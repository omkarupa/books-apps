package com.ou_solutions.booksapps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ou_solutions.booksapps.dto.BooksDto;
import com.ou_solutions.booksapps.dto.BooksResponseDto;
import com.ou_solutions.booksapps.entity.Author;
import com.ou_solutions.booksapps.entity.Book;
import com.ou_solutions.booksapps.repository.AuthorRepository;
import com.ou_solutions.booksapps.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepo;
	
	private final AuthorRepository authorRepo;
	
	public BooksResponseDto createNewBook(BooksDto bookDto)
	{
		
		Book book = null;
		
		if(bookDto.authorName() != null && bookDto.bookName() != null)
		{
			Book createBook =  convertDtoToBookEntity(bookDto);
			
			Author author = authorRepo.findByAuthorName(createBook.getAuthor().getAuthorName());
			
			if(author != null)
			{
				createBook.setAuthor(author);
				
				book =  bookRepo.save(createBook);
				authorRepo.save(book.getAuthor());
			}
			else {
				
				book = bookRepo.save(createBook);
				authorRepo.save(book.getAuthor());
				
				
			}
		}
		
		
		
		System.out.println(book.toString());
		
		List<String> booksList = new ArrayList<>();
		
		booksList.add(book.getName());
		
		return new BooksResponseDto(book.getAuthor().getAuthorName(),booksList);
		
	}
	
	
	
	public Book convertDtoToBookEntity(BooksDto bookDto)
	{
		if(bookDto.authorName() != null && bookDto.bookName() != null)
		{
			Book book =  new Book();
			book.setName(bookDto.bookName());
			
			Author author = new Author();
			author.setAuthorName(bookDto.authorName());
			
			
			book.setAuthor(author);
			
			return book;
	
		}
		
		return null;
	}
	
	public List<BooksResponseDto> findBooks()
	{
		List<Author> authorsList = authorRepo.findAll();
		
		System.out.println(authorsList);
		
		List<BooksResponseDto> responseList = new ArrayList<>();
	
		
		
		return responseList;
	}



	private void convertBookIntoResponse(Book book,List<BooksResponseDto> responseList) {
		
		List<String> booksList = new ArrayList<>();
		
		booksList.add(book.getName());
		
		responseList.add(new BooksResponseDto(book.getAuthor().getAuthorName(),booksList));
		
		
	}



	public BooksResponseDto findByBookName(String bookName) {
		
		Book book =  bookRepo.findByName(bookName);
		
		List<String> booksList = new ArrayList<>();
		
		booksList.add(book.getName());
		
		return new BooksResponseDto(book.getAuthor().getAuthorName(),booksList);
		
	}



	public BooksResponseDto findBooksByAuthorName(String authorName) {
		
		Author author =  authorRepo.findByAuthorName(authorName);
		
		 BooksResponseDto bookResponseDto = new BooksResponseDto(authorName);
		
		List<Book> books =  bookRepo.findByAuthor(author);
		
		System.out.println(books);
		
		List<String> booksNameList = new ArrayList<>();
		
		books.stream().map(book -> booksNameList.add(book.getName())).toList();
		
		bookResponseDto.setBooks(booksNameList);
		
		return bookResponseDto;
		
	}

}
