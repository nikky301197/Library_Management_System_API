package com.librarymanagementapi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementapi.dto.UserDTO;
import com.librarymanagementapi.entity.Role;
import com.librarymanagementapi.entity.User;
import com.librarymanagementapi.exceptionHandling.ResourceNotFoundException;
import com.librarymanagementapi.repository.RoleRepo;
import com.librarymanagementapi.repository.UserRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("library_management_system/api/v1/admin")
public class AdminController {

	@Autowired
	RoleRepo rolerepo;

	@Autowired
	UserRepo userrepo;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping("/roles/add")
	ResponseEntity<List<Role>> addRoles(@RequestBody List<Role> rolelist) {
		List<Role> addedRoles = rolerepo.saveAll(rolelist);
		return new ResponseEntity<List<Role>>(addedRoles, HttpStatus.CREATED);

	}

	@PostMapping("/users/add")
	ResponseEntity<UserDTO> addUsers(@Valid @RequestBody UserDTO dto) throws ResourceNotFoundException {
		User user = modelMapper.map(dto, User.class);
		Set<Role> setrole = new HashSet<>();
		for (Role r : user.getRoles()) {
			Role foundrole = rolerepo.findById(r.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException("Role not with role id : " + r.getRoleId()));
			setrole.add(foundrole);
		}
		user.setRoles(setrole);
		User addedUsers = userrepo.save(user);
		UserDTO userDTO = modelMapper.map(addedUsers, UserDTO.class);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);

	}

	@GetMapping("/welcome")
	String welcomeDashboard() {
		return "welcome admin dashboard";
	}

}
