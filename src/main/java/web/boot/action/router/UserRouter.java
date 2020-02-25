package web.boot.action.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import web.boot.action.handle.UserHandle;

/**
 * it's not work request route /first response 404
 * @author z
 */
@Configuration
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> routeUser(UserHandle userHandle) {
        return RouterFunctions.route(RequestPredicates.GET("/first").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                userHandle::first);
    }
}
