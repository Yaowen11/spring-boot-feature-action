package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import web.boot.action.entity.JsonData;
import web.boot.action.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author z
 */
@RestController
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/users/user")
    public int store(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone) {
        return jdbcTemplate.update("INSERT INTO users (firstName, lastName, phone) values (?, ?, ?)", firstName, lastName, phone);
    }

    @PostMapping("/users/batch")
    public JsonData batchStore(@RequestParam int batch) {
        String insertSql = "INSERT INTO users (firstName, lastName, phone) values (?, ?, ?)";
        List<Object[]> params = new ArrayList<>();
        User current;
        for (int i  = 1; i <= batch; i++) {
            current = User.USERS.get(i);
            String[] user = new String[3];
            user[0] = current.getFirstName();
            user[1] = current.getLastName();
            user[2] = current.getPhone();
            params.add(user);
        }
        int[] result = jdbcTemplate.batchUpdate(insertSql, params);
        return new JsonData(0, "store", Arrays.toString(result));
    }

    @GetMapping("/users/page/{page}")
    public List<User> get(@PathVariable int page, @RequestParam int perPage) {
        String sql = "select * from users limit ?, ?";
        return jdbcTemplate.query(sql, new Object[]{page * perPage - perPage, perPage}, new BeanPropertyRowMapper<>(User.class));
    }

    @GetMapping("/users/index/{id}")
    public Map<String, Object> index(@PathVariable int id) {
        try {
            return jdbcTemplate.queryForMap("select * from users where id = ?", id);
        } catch (EmptyResultDataAccessException ignore) {
            return null;
        }
    }

    @GetMapping("/users/single/{id}")
    public User singleUser(@PathVariable int id) {
        String sql = "select * from users where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException ignore) {
            return null;
        }
    }
}
