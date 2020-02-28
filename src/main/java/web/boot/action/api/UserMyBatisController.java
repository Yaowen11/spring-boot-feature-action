package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.boot.action.dao.UserMapper;
import web.boot.action.entity.User;

import java.util.List;

/**
 * @author z
 */
@RestController
public class UserMyBatisController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/mybatis/username")
    public User getUser(@RequestParam String username) {
        return userMapper.findUserByUsername(username);
    }

    @GetMapping("/user/mybatis/list")
    public List<User> index() {
        return userMapper.findAll();
    }

    @GetMapping("/user/mybatis/{page}")
    public List<User> list(
            @PathVariable int page,
            @RequestParam int perPage,
            @RequestParam String orderColumn,
            @RequestParam String order) {
        return userMapper.index(orderColumn, order, page * perPage - perPage, perPage);
    }
}
