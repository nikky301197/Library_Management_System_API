package com.librarymanagementapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApiApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

//1.Use HTTP Methods Correctly:
//2. Use Nouns in Endpoint URLs:
//3. Versioning
//4. Use Proper Status Codes:
//5. Response Formatting:
//6. Input Validation:
//7. Authentication and Authorization:
//8.Authentication and Authorization
//9. Error Handling
//10. Version Control


