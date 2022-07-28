package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bo.UserBo;
import com.example.demo.payload.UserPayload;
import com.example.demo.services.UserService;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService service;
 
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/image";
	@GetMapping
	public List<UserBo> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserBo getSingleUser(@PathVariable(value = "id") int userId) {
		return service.getUser(userId);
	}

	@PostMapping
	public UserBo createUser(@ModelAttribute UserPayload user) throws Exception {
		return service.createUser(user,uploadDirectory);
	}

	@PutMapping("/{id}")
	public UserBo updateUser(@RequestBody UserPayload user, @PathVariable(value = "id") int userId) {
		return service.updateUser(user, userId);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(value = "id") int userId) {
		service.deleteUser(userId);
	}
}
