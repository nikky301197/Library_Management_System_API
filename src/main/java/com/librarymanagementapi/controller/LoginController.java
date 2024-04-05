package com.librarymanagementapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementapi.dto.JwtRequest;
import com.librarymanagementapi.dto.JwtResponse;
import com.librarymanagementapi.securityConfig.CustomUserDetailsServiceImpl;
import com.librarymanagementapi.securityConfig.JwtUtility;

@RestController
@RequestMapping("library_management_system/api/v1")
public class LoginController {
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationManager authmanager;

	@Autowired
	private JwtUtility jwtutill;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    @PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtrequest)
	{
    	String username = jwtrequest.getUsername();
    	String password = jwtrequest.getPassword();
    	
    	 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
         try {
        	 authmanager.authenticate(authentication);

         } 
         catch (BadCredentialsException e) {
             throw new BadCredentialsException(" Invalid Username or Password  !!");
         }
		

         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         String token = this.jwtutill.GenerateToken(userDetails.getUsername());
          System.out.println("token  "+token);
         JwtResponse response = new JwtResponse(token);
         return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
