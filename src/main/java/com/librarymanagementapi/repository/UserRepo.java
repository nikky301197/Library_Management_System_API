package com.librarymanagementapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementapi.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);
}
