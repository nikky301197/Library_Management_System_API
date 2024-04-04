package com.librarymanagementapi.dto;

import java.util.Date;

import com.librarymanagementapi.entity.Book;
import com.librarymanagementapi.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssuedBookRequestDTO {
	private Long id;
	private BookRequestDto book;
	private StudentRequestDto student;
	private Date issueDate;
	private Date dueDate;
}
