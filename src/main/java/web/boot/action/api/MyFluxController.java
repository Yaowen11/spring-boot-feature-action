package web.boot.action.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import web.boot.action.entity.User;
import java.util.LinkedList;
import java.util.List;


/**
 * 基于注解的 flux 配置
 * @author z
 */
@RestController
@RequestMapping("/users")
public class MyFluxController {
    @GetMapping("/{user}")
    public Mono<User> getUser(@PathVariable int user) {
        return Mono.just(User.USERS.get(user));
    }
    @GetMapping("/list/{page}")
    public Mono<List<User>> index(@PathVariable int page) {
        int pageSize = 3;
        int startId = (page - 1) * 3 + 1;
        List<User> users = new LinkedList<>();
        for (int i = startId; i < startId + pageSize; i++) {
            users.add(User.USERS.get(i));
        }
        return Mono.just(users);
    }
}
