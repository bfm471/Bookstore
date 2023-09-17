package backEnd.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backEnd.Bookstore.domain.Book;
import backEnd.Bookstore.domain.BookRepository;

@RestController
public class RestBookController {

	@Autowired
	private BookRepository repository;
	
	@GetMapping("/books")
	public Iterable<Book> getBooks() {
		return repository.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> findBookById(@PathVariable("id")Long id) {
		return repository.findById(id);
	}
}
