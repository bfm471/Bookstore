package backEnd.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backEnd.Bookstore.domain.Book;
import backEnd.Bookstore.domain.BookRepository;
import backEnd.Bookstore.domain.Category;
import backEnd.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrepository) {
	return (args) -> {
		
		catrepository.save(new Category("Action and Adventure"));
		catrepository.save(new Category("Satire"));
		catrepository.save(new Category("Romance"));
		catrepository.save(new Category("Classics"));
		
		repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 19.90, catrepository.findByName("Romance").get(0)));
		repository.save(new Book("Animal Farm", "George Orwell", 1945, "2212343-51", 14.95, catrepository.findByName("Satire").get(0)));
		repository.save(new Book("To Kill a Mockinbird", "Harper Lee", 1960, "951-864-028-9", 15.90, catrepository.findByName("Classics").get(0)));
		
	};
	}


}
