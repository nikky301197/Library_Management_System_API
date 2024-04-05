package com.librarymanagementapi.dto;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

	@Email(message = "username required valid email id")
	@NotEmpty(message = "username cannot be blank  ")
	private String username;

	@Size(min = 8, max = 15, message = "password can be minimum 8 character long and maximum 15 ")
	@NotEmpty(message = "password cannot be blank")	
	private String password;

	private Set<RoleDTO> roles;
}
