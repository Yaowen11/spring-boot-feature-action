package web.boot.action.handle;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import web.boot.action.entity.UserMongoEntity;
import web.boot.action.repository.UserMongoRepository;

/**
 * @author z
 */
@Component
public class UserMongoHandler {
    private final UserMongoRepository repository;

    public UserMongoHandler(UserMongoRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> saveUser(ServerRequest request) {
        Mono<UserMongoEntity> userMongoEntityMono = request.bodyToMono(UserMongoEntity.class);
        return ServerResponse.ok().build(repository.insert(userMongoEntityMono).then());
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
          Long userId = Long.parseLong(request.pathVariable("id"));
          return ServerResponse.ok().build(repository.deleteById(userId).then());
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        Long userId = Long.parseLong(request.pathVariable("id"));
        Mono<UserMongoEntity> userMongoEntityMono = repository.findById(userId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMongoEntityMono, UserMongoEntity.class);
    }

    public Mono<ServerResponse> listUser(ServerRequest request) {
        Flux<UserMongoEntity> userMongoEntityFlux = repository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMongoEntityFlux, UserMongoEntity.class);
    }
}
