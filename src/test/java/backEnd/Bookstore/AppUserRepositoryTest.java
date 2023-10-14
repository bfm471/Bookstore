package backEnd.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backEnd.Bookstore.domain.AppUser;
import backEnd.Bookstore.domain.AppUserRepository;

@DataJpaTest
public class AppUserRepositoryTest {
	@Autowired
	private AppUserRepository repository;
	
	@Test
	public void findUserByUsername() {
		AppUser appUser = repository.findByUsername("user");
		assertThat(appUser.getUserId()).isEqualTo(1);
	}
	
	@Test
	public void createNewAppUser() {
		AppUser appUser = new AppUser("newUser", "$2a$10$3wlYquLnCxQNRdj11uee.uRGqkqVobP8ZpjPs/rkiCV2oqvv0aj1i",
				"newuser@nw.com", "USER");
		repository.save(appUser);
		assertThat(appUser.getUserId()).isNotNull();
	}
	
	@Test
	public void deleteAppUser() {
		List<AppUser> appUsers = repository.findListByUsername("user");
		AppUser appuser = appUsers.get(0);
		repository.delete(appuser);
		List<AppUser> newAppUsers = repository.findListByUsername("user");
		assertThat(newAppUsers).hasSize(0);
	}
	
	@Test
	public void updateAppUser() {
		Optional<AppUser> appuser = repository.findById((long) (1));
		assertThat(appuser.get().getUserId()).isNotNull();
		appuser.get().setUsername("testingforupdate");
		List<AppUser> appusers = repository.findListByUsername("testingforupdate");
		assertThat(appusers).hasSize(1);		
	}
	

}
