package com.zg.r2.demo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zg.r2.demo.author.Author;
import com.zg.r2.demo.author.AuthorService;
import com.zg.r2.demo.publisher.IPublisherService;
import com.zg.r2.demo.publisher.PublisherService;

@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private IPublisherService publisherService;
	
	public Book save(Book  book,long  pubid) {
		
		book.setPublisher(publisherService.findById(pubid));
		return bookRepository.save(book);
	}
	
	public List<Book> getAll(){
		
			return bookRepository.findAll();
		
	}

	public void delete(long id) {
		
			bookRepository.deleteById((id));
		
	}
	
	public Book findById(long id) {
		return bookRepository.findById(id).get();
	}
	public List<Book> findByAuthor(Author author) {
		return bookRepository.findByAuthor(author);
	}
	public Book edit(Book book, Long pubid) {
		book.setPublisher(publisherService.findById(pubid));
		return bookRepository.save(book);
	}

	public List<Book> findByName(String name) {
		// TODO Auto-generated method stub
		return bookRepository.findByNameContains(name);
	}
	public List<Book> findByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return bookRepository.findByIsbn(isbn);
	}
	
	public List<Book> findBySeri(String seri) {
		// TODO Auto-generated method stub
		return bookRepository.findBySeriname(seri);
	}


}
