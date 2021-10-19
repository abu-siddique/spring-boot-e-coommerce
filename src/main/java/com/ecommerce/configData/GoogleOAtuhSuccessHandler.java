package com.ecommerce.configData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleOAtuhSuccessHandler implements AuthenticationSuccessHandler{

	final UserRepository userRepository;
	final RoleRepository roleRepository;
	public RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findByEmail(email).isPresent()) {
			
		}else {
			User user = new User();
			user.setFirstname(token.getPrincipal().getAttributes().get("given_name").toString());
			String lastname = null;
			if(token.getPrincipal().getAttributes().get("family_name") !=null) {
				lastname = token.getPrincipal().getAttributes().get("family_name").toString();
			}
			user.setId((long) 2);;
			user.setLastname(lastname);
			user.setEmail(email);
			List<Role> roles = new ArrayList<>();
			Long id = (long) 2;
			roles.add(roleRepository.findById(id).get());
			userRepository.save(user);
			
		}
		
		redirectStrategy.sendRedirect(request, response, "/home");
		
	}
}
