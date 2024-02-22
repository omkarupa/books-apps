package com.ou_solutions.booksapps.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ou_solutions.booksapps.dto.BooksDto;
import com.ou_solutions.booksapps.dto.BooksResponseDto;
import com.ou_solutions.booksapps.entity.Book;
import com.ou_solutions.booksapps.service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books-apps")
@RequiredArgsConstructor
public class BooksController {
	
	private final BookService bookService;
	
	@GetMapping("/books")
	public List<BooksResponseDto> findBooks()
	{
		return bookService.findBooks();
	}
	
	@GetMapping("/books/{bookName}")
	public BooksResponseDto findByBookByName(@PathVariable String bookName)
	{
		return bookService.findByBookName(bookName);
	}
	
	@GetMapping("/author")
	public BooksResponseDto findBooksByAuthorNameJson(HttpServletRequest request)
	{
		String authorName = request.getHeader("authorName");
		return bookService.findBooksByAuthorName(authorName);
	}
	
	@GetMapping("/book")
	public BooksResponseDto findByBookByNameJson(HttpServletRequest request)
	{
		String bookName = request.getHeader("bookName");
		
		return bookService.findByBookName(bookName);
	}
	
	
	
	@PostMapping("/books")
	public BooksResponseDto createNewBook(@RequestBody BooksDto booksDto)
	{
		return bookService.createNewBook(booksDto);
	}

}
