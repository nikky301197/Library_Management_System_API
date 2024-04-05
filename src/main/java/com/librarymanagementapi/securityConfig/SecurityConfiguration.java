package com.librarymanagementapi.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	@Autowired
	JwtFilter jwtFilter;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsServiceImpl);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	private void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(customizer -> {
customizer.requestMatchers(HttpMethod.POST, "/library_management_system/api/v1/admin/roles/add").hasAnyAuthority("ROLE_ADMIN");
customizer.requestMatchers(HttpMethod.POST, "/library_management_system/api/v1/admin/users/add").hasAnyAuthority("ROLE_ADMIN");
customizer.requestMatchers(HttpMethod.GET, "/library_management_system/api/v1/admin/welcome").hasAnyAuthority("ROLE_ADMIN");
customizer.requestMatchers(HttpMethod.POST, "/library_management_system/api/v1/staff/issuedBooks/add").hasAnyAuthority("ROLE_ADMIN", "ROLE_STAFF");
customizer.requestMatchers(HttpMethod.POST, "/library_management_system/api/v1/staff/books/add").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF");
customizer.requestMatchers(HttpMethod.POST, "/library_management_system/api/v1/staff/students/add").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF");
customizer.requestMatchers(HttpMethod.GET, "/library_management_system/api/v1/staff/welcome").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF");
customizer.requestMatchers(HttpMethod.GET, "/library_management_system/api/v1/student/welcome").hasAnyAuthority("ROLE_ADMIN","ROLE_STUDENT");
customizer.requestMatchers(HttpMethod.GET, "/library_management_system/api/v1/student/books/get").hasAnyAuthority("ROLE_ADMIN","ROLE_STUDENT");		
customizer.requestMatchers(HttpMethod.POST, "/library_management_system/api/v1/login").permitAll();	
}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}
}
