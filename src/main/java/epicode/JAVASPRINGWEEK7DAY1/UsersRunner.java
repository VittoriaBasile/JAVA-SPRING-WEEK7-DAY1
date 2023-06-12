package epicode.JAVASPRINGWEEK7DAY1;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.JAVASPRINGWEEK7DAY1.entities.User;
import epicode.JAVASPRINGWEEK7DAY1.repositories.UsersRepository;

@Component
public class UsersRunner implements CommandLineRunner {
	@Autowired
	UsersRepository usersRepo;
	@Autowired
	PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		List<User> usersDB = usersRepo.findAll();

		if (usersDB.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String username = faker.name().username();
					String nome = faker.name().firstName();

					String email = faker.internet().emailAddress();
					String psw = bcrypt.encode(faker.internet().password());
					User user = new User(username, nome, email, psw);
					usersRepo.save(user);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
