package web.boot.action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.boot.action.entity.Goods;

/**
 * goods jpa
 * @author z
 */
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
