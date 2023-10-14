package backEnd.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backEnd.Bookstore.domain.Book;
import backEnd.Bookstore.domain.BookRepository;
import backEnd.Bookstore.domain.Category;
import backEnd.Bookstore.domain.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository catrepository;
		
	@Test
	public void findAllBooks() {
		Iterable<Book> books = repository.findAll();
		assertThat(books).hasSize(3);
	}
	
	@Test
	public void findByTitle() {
		List<Book> books = repository.findByTitle("Animal Farm");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
	}
		
	@Test
	public void createNewBook() {
		Category category = new Category("Testier");
		catrepository.save(category);
		
		Book book = new Book("The great test", "Testy Tester", 1999, "1234567-89", 19.99, category);
		repository.save(book);
		
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = repository.findByTitle("Animal Farm");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("Animal Farm");
		assertThat(newBooks).hasSize(0);
	}
	
	@Test
	public void updateBook() {
		Optional<Book> book = repository.findById((long) (1));
		assertThat(book.get().getId()).isNotNull();
		book.get().setTitle("testingforupdate");
		List<Book> books = repository.findByTitle("testingforupdate");
		assertThat(books).hasSize(1);		
	}
	
	@Test
	public void getNonExistingBook() {
		Optional<Book> book = repository.findById((long) 50);
		assertThat(book).isEmpty();		
	}
}
