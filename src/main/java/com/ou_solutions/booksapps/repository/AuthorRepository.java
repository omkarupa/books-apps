package com.ou_solutions.booksapps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ou_solutions.booksapps.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	public Author findByAuthorName(String authorName);

}
