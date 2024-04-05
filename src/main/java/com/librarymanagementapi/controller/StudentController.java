package com.librarymanagementapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementapi.dto.BookResponseDto;
import com.librarymanagementapi.entity.Book;
import com.librarymanagementapi.repository.BookRepo;

@RestController
@RequestMapping("library_management_system/api/v1/student")
public class StudentController {
	@Autowired
	BookRepo bookrepo;
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/books/get")
	ResponseEntity<List<BookResponseDto>> avalibleBooks() {

		List<Book> booklist = bookrepo.findAll();
		List<BookResponseDto> booklistdto = booklist.stream().map(book -> modelMapper.map(book, BookResponseDto.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(booklistdto);
	}
	
	@GetMapping("/welcome")
	String welcome()
	{
		return "welcome student dashboard";
	}

}
