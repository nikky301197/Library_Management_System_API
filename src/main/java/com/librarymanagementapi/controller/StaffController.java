package com.librarymanagementapi.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementapi.dto.BookRequestDto;
import com.librarymanagementapi.dto.BookResponseDto;
import com.librarymanagementapi.dto.IssuedBookRequestDTO;
import com.librarymanagementapi.dto.IssuedBookResponseDto;
import com.librarymanagementapi.dto.StudentRequestDto;
import com.librarymanagementapi.dto.StudentResponseDto;
import com.librarymanagementapi.entity.Book;
import com.librarymanagementapi.entity.IssuedBooks;
import com.librarymanagementapi.entity.Student;
import com.librarymanagementapi.exceptionHandling.ResourceNotFoundException;
import com.librarymanagementapi.repository.BookRepo;
import com.librarymanagementapi.repository.IssuedBookRepo;
import com.librarymanagementapi.repository.StudentRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("library_management_system/api/v1/admin")
public class StaffController {

	@Autowired
	BookRepo bookRepo;
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	IssuedBookRepo issuedBookRepo;
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/welcome")
	String welcomeDashboard() {
		return "welcome dashboard";
	}

	@PostMapping("/books/add")
	ResponseEntity<BookResponseDto> addBookInLibarary(@Valid @RequestBody BookRequestDto bookrequestdto) {
		Book book = modelMapper.map(bookrequestdto, Book.class);
		Book newBook = bookRepo.save(book);
		BookResponseDto bookresponse = modelMapper.map(newBook, BookResponseDto.class);
		return new ResponseEntity<>(bookresponse, HttpStatus.CREATED);
	}

	@PostMapping("/students/add")
	ResponseEntity<StudentResponseDto> addStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {
		Student student = modelMapper.map(studentRequestDto, Student.class);
		Student newStudent = studentRepo.save(student);
		StudentResponseDto studentResponseDto = modelMapper.map(newStudent, StudentResponseDto.class);
		return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
	}

	@PostMapping("/issuedBooks/add")
	ResponseEntity<IssuedBookResponseDto> issueBookToStudent(@RequestBody IssuedBookRequestDTO issuedBookRequestDTO)
			throws ResourceNotFoundException {
		Long studentId = issuedBookRequestDTO.getStudent().getStudentId();
		System.out.println(studentId);
		Long bookId = issuedBookRequestDTO.getBook().getBookId();
		System.out.println(bookId);
		Book foundBook = bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with book id :" + bookId));
		Student foundStudent = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with student id :" + studentId));

		IssuedBooks newBook = modelMapper.map(issuedBookRequestDTO, IssuedBooks.class);

		newBook.setBook(foundBook);
		newBook.setStudent(foundStudent);
		newBook.setIssueDate(new Date());
		newBook.setDueDate(new Date());
		IssuedBooks savedBook = issuedBookRepo.save(newBook);
		IssuedBookResponseDto savedDto = modelMapper.map(savedBook, IssuedBookResponseDto.class);
		return new ResponseEntity<IssuedBookResponseDto>(savedDto, HttpStatus.CREATED);

	}

	@GetMapping("/books/get")
	ResponseEntity<List<IssuedBookResponseDto>> fetchIssuedBooksDetail() {
		List<IssuedBooks> listIssueBook = issuedBookRepo.findAll();
		System.out.println(listIssueBook);
		List<IssuedBookResponseDto> listIssueBookDto = listIssueBook.stream()
				.map(issueBook -> modelMapper.map(issueBook, IssuedBookResponseDto.class)).collect(Collectors.toList());
		System.out.println(listIssueBookDto);

		return ResponseEntity.ok(listIssueBookDto);
	}

}
