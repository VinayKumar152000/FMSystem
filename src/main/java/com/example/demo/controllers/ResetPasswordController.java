package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ResetPasswordPayload;
import com.example.demo.services.ResetPasswordService;

@RestController
@RequestMapping("/api/resetpassword")
public class ResetPasswordController {
	
	@Autowired
	ResetPasswordService service;

	@PostMapping
	public String resetPassword(@RequestBody ResetPasswordPayload payload) {
		
		return service.resetPassword(payload);
	}
}
