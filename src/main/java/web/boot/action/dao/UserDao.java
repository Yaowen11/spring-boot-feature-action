package web.boot.action.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import web.boot.action.entity.User;

/**
 * @author z
 */
public class UserDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public UserDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    public User insert() {
        return new User();
    }
}
