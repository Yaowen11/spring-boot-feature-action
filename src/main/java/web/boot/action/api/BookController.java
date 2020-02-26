package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import web.boot.action.entity.Book;
import web.boot.action.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author z
 */
@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/books/book")
    public Book store(@RequestParam String author, @RequestParam String title,
                      @RequestParam float price, @RequestParam int online, @RequestParam String description,
                      @RequestParam int saleAmount) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setPrice(price);
        book.setOnline(online);
        book.setDescription(description);
        book.setSaleAmount(saleAmount);
        return bookRepository.save(book);
    }

    @PatchMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestParam String author, @RequestParam String title,
                      @RequestParam float price, @RequestParam int online, @RequestParam String description,
                      @RequestParam int saleAmount) {
        Book book = bookRepository.getOne(id);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPrice(price);
        book.setOnline(online);
        book.setDescription(description);
        book.setSaleAmount(saleAmount);
        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void destroy(@PathVariable int id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/books/book/{id}")
    public Optional<Book> one(@PathVariable int id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/books/index/{page}")
    public Page<Book> index(@PathVariable int page, @RequestParam int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return bookRepository.findAll(pageable);
    }
}
