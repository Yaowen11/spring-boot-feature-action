package web.boot.action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Indexed;
import web.boot.action.entity.Book;


/**
 * @author z
 */
@Indexed
public interface BookRepository extends JpaRepository<Book, Integer> {

}

