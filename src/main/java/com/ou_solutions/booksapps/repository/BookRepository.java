package com.ou_solutions.booksapps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ou_solutions.booksapps.entity.Author;
import com.ou_solutions.booksapps.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByName(String bookName);

	List<Book> findByAuthor(Author author);
	

}
