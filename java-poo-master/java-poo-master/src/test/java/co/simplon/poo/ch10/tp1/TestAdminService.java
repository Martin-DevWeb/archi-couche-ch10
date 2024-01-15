package co.simplon.poo.ch10.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.impl.UserRepositoryJson;
import co.simplon.poo.ch10.tp1.service.AdminService;
import co.simplon.poo.ch10.tp1.service.impl.AdminServiceImpl;

public class TestAdminService {
	private UserRepositoryJson users = new UserRepositoryJson("data/json/users.json");
	private AdminService adminService = new AdminServiceImpl(users);

	@BeforeEach
	void beforeEachTest() throws IOException {
		users.deleteAll();
	}

	@Test
	void findAllUsersTest() throws Exception {
		users.create(new User("Stef", "p@$$w0rd", "stef@secudev.net", true));
		users.create(new User("Bob", "p@$$w0rdSeCure", "bobperso@gmail.com", true));
		System.out.println(users.retrieve());
		assertEquals(users.retrieve(), adminService.findAllUsers());
	}

	@Test
	void resetAndSendNewPasswordTest() throws Exception {
		users.create(new User("Bobby", "p@$$w0rdSeCuredOfc", "bobbylemalin@gmail.com", true));
		User bobby = users.getByLogin("Bobby");
		adminService.resetAndSendNewPassword(bobby.getId());

		assertNotEquals("p@$$w0rdSeCuredOfc", bobby.getPassword());
	}

	@Test
	void disableUserTest() throws Exception {
		users.create(new User("John", "1234", "johndoe@gmail.com", true));
		User john = users.getByLogin("John");
		adminService.disableUser(john.getId());

		assertEquals(false, john.isEnable());
	}

	@Test
	void enableUserTest() throws Exception {
		users.create(new User("Jude", "secretAgent", "cia-secret@gmail.com", false));
		User jude = users.getByLogin("Jude");
		adminService.enableUser(jude.getId());

		assertEquals(true, jude.isEnable());
	}
}
