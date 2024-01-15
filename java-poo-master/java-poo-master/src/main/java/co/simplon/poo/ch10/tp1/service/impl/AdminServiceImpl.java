package co.simplon.poo.ch10.tp1.service.impl;

import java.util.List;

import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.UserRepository;
import co.simplon.poo.ch10.tp1.service.AdminService;
import co.simplon.poo.ch10.tp1.utils.communication.FakeMailUtil;
import co.simplon.poo.ch10.tp1.utils.security.PasswordTools;

public class AdminServiceImpl implements AdminService {

	private final UserRepository users;

	public AdminServiceImpl(UserRepository users) {
		this.users = users;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = this.users.retrieve();
		return users;
	}

	@Override
	public void resetAndSendNewPassword(String userId) {
		User targetUser = users.getById(userId);
		String newPass = PasswordTools.generateRandomPassword();

		targetUser.setPassword(newPass);
		FakeMailUtil.sendFakeEmail("Mot de passe réinitialisé", targetUser.getLogin(),
				"Voici votre nouveau mot de passe provisoire : '" + newPass
						+ "'. Veuillez le changer rapidement par le mot de passe qui vous convient.");
	}

	@Override
	public void disableUser(String userId) throws Exception {
		User targetUser = users.getById(userId);
		targetUser.setEnable(false);
	}

	@Override
	public void enableUser(String userId) throws Exception {
		User targetUser = users.getById(userId);
		targetUser.setEnable(true);
	}

}
