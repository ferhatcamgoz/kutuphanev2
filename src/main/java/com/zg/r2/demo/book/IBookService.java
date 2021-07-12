package com.zg.r2.demo.book;

import java.util.List;

import com.zg.r2.demo.author.Author;

public interface IBookService {
	public Book save(Book  book,long  pubid);
	public List<Book> getAll();
	public void delete(long id);
	public  Book findById(long id);
	public  List<Book> findByAuthor(Author author);
	public  Book edit(Book book, Long pubid);
	public  List<Book> findByName(String name);
	public   List<Book> findByIsbn(String isbn);
	public  List<Book> findBySeri(String seri);
}
