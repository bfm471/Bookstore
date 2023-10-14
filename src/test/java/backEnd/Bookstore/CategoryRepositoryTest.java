package backEnd.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backEnd.Bookstore.domain.Category;
import backEnd.Bookstore.domain.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
	@Autowired
	CategoryRepository repository;
	
	@Test
	public void findByName() {
		List<Category> categories = repository.findByName("Satire");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getCategoryId()).isEqualTo(2);
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Test category");
		repository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		List<Category> categories = repository.findByName("Romance");
		Category category = categories.get(0);
		repository.delete(category);
		List<Category> newCategories = repository.findByName("Romance");
		assertThat(newCategories).hasSize(0);
	}
	
	@Test
	public void getNonExistingCategory() {
		Optional<Category> category = repository.findById((long) 50);
		assertThat(category).isEmpty();		
	}

}
