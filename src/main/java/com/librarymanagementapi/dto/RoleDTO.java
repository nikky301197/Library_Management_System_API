package com.librarymanagementapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    
	@NotEmpty
	private Integer roleId;
	@NotEmpty
	private String roleName;
}
