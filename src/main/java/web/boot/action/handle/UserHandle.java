package web.boot.action.handle;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import web.boot.action.entity.User;

/**
 * it's not work
 * @author z
 */
@Component
public class UserHandle {
    public Mono<ServerResponse> first(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(User.USERS.get(1)));
    }
}
