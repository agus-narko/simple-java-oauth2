package com.agus.java.resource.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agus.java.model.userManagement.LoginUser;
import com.agus.java.model.userManagement.UserEntity;



@Service
public class CustomDetailsService implements UserDetailsService {
	@Autowired
	GetUserbyUsername login;

	@Override
	public CustomUser loadUserByUsername(final String username) {
		LoginUser loginUser = null;
		try {
			loginUser = login.getUserDetails(username);
			CustomUser customUser = new CustomUser(loginUser);
			return customUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User " + username
					+ " tidak terdaftar");
		}
	}
}