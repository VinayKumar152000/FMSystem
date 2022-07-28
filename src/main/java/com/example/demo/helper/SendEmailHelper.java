package com.example.demo.helper;

import java.util.*;

import javax.mail.internet.MimeMessage;

import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ForgotPassword;
import com.example.demo.domain.User;
import com.example.demo.repository.ForgotPasswordRepository;

import freemarker.template.Configuration;

@Service
public class SendEmailHelper {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	Configuration configuration;

	@Autowired
	ForgotPasswordRepository repo;

	public void sendEmail(User user) throws Exception {

//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("vkvinay217@gmail.com");
//		message.setTo(user.getEmail());
//		message.setSubject("Registration Succesful");

		// auto generated password

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setSubject("Registration Succesfull");
		helper.setTo(user.getEmail());

		int num = (int) (Math.random() * 1000000);
		String password = num + "";

		user.setPassword(password);
//		String mailBody = user.getName() + " " + user.getNumber() + " " + user.getRoles() + " " + user.getAddress()
//				+ " " + user.isVerificationStatus() + " " + user.getPassword();
//		
		String emailContent = getEmailContent(user);
		helper.setText(emailContent, true);
		mailSender.send(mimeMessage);
		System.out.println("Mailsend successfully");
	}

	String getEmailContent(User user) throws Exception {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("user", user);
		configuration.getTemplate("EmailTemplate.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}

	public int forgotPasswordEmail(User user) throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setSubject("Reset Your Password Using Below Link");
		helper.setTo(user.getEmail());

		int num = (int) (Math.random() * 1000000);
		String response = "http://localhost:8080/reset-password?token=" + num;
		helper.setText(response, true);
		mailSender.send(mimeMessage);

		ForgotPassword forgotpassword = new ForgotPassword();
		forgotpassword.setToken(num + "");
		forgotpassword.setUser(user);

		repo.save(forgotpassword);

		return num;

	}
}
