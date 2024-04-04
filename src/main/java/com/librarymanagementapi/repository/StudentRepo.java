package com.librarymanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementapi.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
