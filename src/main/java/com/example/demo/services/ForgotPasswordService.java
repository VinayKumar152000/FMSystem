package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.User;
import com.example.demo.helper.SendEmailHelper;
import com.example.demo.payload.ForgotPasswordPayload;
import com.example.demo.repository.UserRepository;

@Service
public class ForgotPasswordService {

	@Autowired
	UserRepository repo;
	
	@Autowired
	SendEmailHelper sendEmailHelper;

	public int forgotPassword(ForgotPasswordPayload payload) throws Exception {
		String email = payload.getEmail();

		User user = repo.findByEmail(email);

		if (user == null) {
			throw new Exception("User not found with email given");
		}

		return sendEmailHelper.forgotPasswordEmail(user);
	}
}
