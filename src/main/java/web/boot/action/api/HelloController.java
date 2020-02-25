package web.boot.action.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author z
 */
@RestController
public class HelloController {
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name, @RequestParam int age) {
        return "Hello, " + name + " Your age is " + age;
    }
}
