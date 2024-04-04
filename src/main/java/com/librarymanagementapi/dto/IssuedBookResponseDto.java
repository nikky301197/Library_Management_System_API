package com.librarymanagementapi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssuedBookResponseDto {
	private Long id;
	private BookRequestDto book;
	private StudentRequestDto student;
	private Date issueDate;
	private Date dueDate;
}
