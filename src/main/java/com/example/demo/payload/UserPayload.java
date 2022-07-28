package com.example.demo.payload;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {

	private String name;
	private String address;
	private String number;
	private String email;
	private List<String> roles;
	private MultipartFile image;
	private boolean verificationStatus;
	private String password;
}
