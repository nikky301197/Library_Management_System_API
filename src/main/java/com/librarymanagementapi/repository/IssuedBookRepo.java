package com.librarymanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementapi.entity.IssuedBooks;

public interface IssuedBookRepo extends JpaRepository<IssuedBooks, Long> {

}
