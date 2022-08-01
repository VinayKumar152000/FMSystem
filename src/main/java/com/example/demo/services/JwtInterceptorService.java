package com.example.demo.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
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
		String requestTokenHeader = request.getHeader("Authorization");
		String method = request.getMethod();

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwttuil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// fine
			UserDetails userdetails = this.customuserdetailService.loadUserByUsername(username);
			System.out.println(userdetails.getAuthorities());
			Collection<? extends GrantedAuthority> roles = userdetails.getAuthorities();

//
//			roles.stream().forEach(s -> System.out.println(s));
			List<? extends GrantedAuthority> list = roles.stream()
					.filter(role -> role.getAuthority().equals("ROLE_admin")).collect(Collectors.toList());

			System.out.println(list.size());

			if (method.equals("POST") && request.getRequestURI().equals("api/roles") && list.size() == 0) {
				response.sendError(401, "Unauthorized");
				return false;
			}
			if ((method.equals("DELETE") || method.equals("PUT")) && list.size() == 0) {
				response.sendError(401, "Unauthorized");
				return false;
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
//				System.out.println("Woorking");

				UsernamePasswordAuthenticationToken usernamepasswordauthenticationToken = new UsernamePasswordAuthenticationToken(
						userdetails, null, userdetails.getAuthorities());

				usernamepasswordauthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationToken);
				System.out.println(SecurityContextHolder.getContext().getAuthentication());
			} else {
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