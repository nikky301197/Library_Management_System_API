package com.librarymanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponseDto {
	private Long studentId;
	private String studentName;
	private String email;
	private Integer phoneNo;

}
