package com.librarymanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementapi.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
