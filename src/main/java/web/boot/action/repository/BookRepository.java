package web.boot.action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.boot.action.entity.Book;

import java.util.List;


/**
 * @author z
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
    /**
     * 获取作者书籍
     * @param author 作者
     * @return List</Book>
     */
    @Query("select b from books b where b.author = :author")
    List<Book> findAuthorBooks(@Param("author") String author);
}

