package com.ou_solutions.booksapps.dto;

import java.util.List;

import com.ou_solutions.booksapps.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksResponseDto {
	
	public BooksResponseDto(String authorName2) {
		this.authorName = authorName2;
	}

	private String authorName;
	
	private List<String> books;

}
