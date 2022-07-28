package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ForgotPassword;
import com.example.demo.domain.User;
import com.example.demo.payload.ResetPasswordPayload;
import com.example.demo.repository.ForgotPasswordRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ResetPasswordService {

	@Autowired
	ForgotPasswordRepository repo;

	@Autowired
	UserRepository userrepo;

	public String resetPassword(ResetPasswordPayload payload) {

		ForgotPassword forgotpassword = repo.findByToken(payload.getSentCode());
		User user = forgotpassword.getUser();
		if (user != null && payload.getNewPassword().equals(payload.getConfirmPassword())) {
			user.setPassword(payload.getNewPassword());
		}

		userrepo.save(user);
		repo.deleteById(forgotpassword.getId());
		return "Password Updated  Succesfully";
	}
}
