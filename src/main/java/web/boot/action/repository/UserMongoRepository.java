package web.boot.action.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import web.boot.action.entity.UserMongoEntity;

/**
 * @author z
 */
public interface UserMongoRepository extends ReactiveMongoRepository<UserMongoEntity, Long> {
}
