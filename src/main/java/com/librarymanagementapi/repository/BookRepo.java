package com.librarymanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementapi.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
