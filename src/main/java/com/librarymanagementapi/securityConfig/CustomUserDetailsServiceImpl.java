package com.librarymanagementapi.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.librarymanagementapi.entity.User;
import com.librarymanagementapi.repository.UserRepo;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = repo.
				findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("User not found wiht username : "+username));
       System.out.println(user );
       System.out.println(user.getRoles() );
		return user;

	}

}
