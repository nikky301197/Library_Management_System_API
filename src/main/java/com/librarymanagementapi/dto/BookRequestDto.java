package com.librarymanagementapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequestDto {
//	hello
	private Long bookId;
	@NotEmpty(message = "book name cannot be empty or null")
	private String bookName;
}
