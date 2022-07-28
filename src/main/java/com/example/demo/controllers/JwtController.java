package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.JwtResponseBo;
import com.example.demo.config.JwtUtil;
import com.example.demo.payload.JwtRequestPayload;
import com.example.demo.services.CustomUserDetailService;

@RestController
@RequestMapping("/api/token")
public class JwtController {

	@Autowired
	AuthenticationManager authenticationManger;

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	JwtUtil jwtutil;

	@PostMapping
	public ResponseEntity<?> generateToken(@RequestBody JwtRequestPayload jwtrequest) throws Exception {

		try {
			this.authenticationManger.authenticate(
					new UsernamePasswordAuthenticationToken(jwtrequest.getEmail(), jwtrequest.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Bad Cridentials");
		}

		// fine area

		UserDetails userdetails = this.customUserDetailService.loadUserByUsername(jwtrequest.getEmail());
		String token = this.jwtutil.generateToken(userdetails);
		return ResponseEntity.ok(new JwtResponseBo(token));
	}

}
