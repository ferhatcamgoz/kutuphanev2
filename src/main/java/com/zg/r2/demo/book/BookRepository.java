package com.zg.r2.demo.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zg.r2.demo.author.Author;

public interface BookRepository extends JpaRepository<Book,Long> {

	List<Book> findByAuthor(Author author);
	
	List<Book> findByNameContains(String name);
	List<Book> findByIsbn(String isbn);
	List<Book> findBySeriname(String seriname);

}
