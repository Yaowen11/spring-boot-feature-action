package web.boot.action.dao;

import org.apache.ibatis.annotations.*;
import web.boot.action.entity.User;

import java.util.List;

/**
 * @author z
 */
@Mapper
public interface UserMapper {
    /**
     * find
     * @param username string
     * @return User
     */
    User findUserByUsername(String username);

    /**
     * list user
     * @return users
     */
    @Select("select * from users")
    List<User> findAll();

    /**
     * 列表
     * @param orderColumn 排序字段
     * @param order 顺序
     * @param page page
     * @param perPage size
     * @return list
     */
    List<User> index(String orderColumn, String order, int page, int perPage);
}
