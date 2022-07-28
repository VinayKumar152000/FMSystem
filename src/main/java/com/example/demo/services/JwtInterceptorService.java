package com.example.demo.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.JwtUtil;

@Component
public class JwtInterceptorService implements HandlerInterceptor {

	@Autowired
	CustomUserDetailService customuserdetailService;

	@Autowired
	JwtUtil jwttuil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("pre handling");
		
		String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwttuil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("pre handling");
			// fine
			UserDetails userdetails = this.customuserdetailService.loadUserByUsername(username);
			System.out.println(userdetails.getAuthorities());
			 System.out.println(SecurityContextHolder.getContext().getAuthentication());
			 
			if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println("Woorking");

				UsernamePasswordAuthenticationToken usernamepasswordauthenticationToken = new UsernamePasswordAuthenticationToken(
						userdetails, null, userdetails.getAuthorities());

				usernamepasswordauthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationToken);
				 System.out.println(SecurityContextHolder.getContext().getAuthentication());
			}
			else {
				throw new UsernameNotFoundException("User Not Found");
			}

		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("Post Handle method is Calling");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		System.out.println("Request and Response is completed");
	}
}