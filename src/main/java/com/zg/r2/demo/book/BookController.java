package com.zg.r2.demo.book;


import com.zg.r2.demo.author.Author;

import com.zg.r2.demo.author.AuthorRepository;
import com.zg.r2.demo.author.AuthorService;
import com.zg.r2.demo.author.CurrnetUser;
import com.zg.r2.demo.author.IAuthorService;
import com.zg.r2.demo.publisher.IPublisherService;
import com.zg.r2.demo.publisher.Publisher;

import com.zg.r2.demo.publisher.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class BookController {
    
    
    private final IAuthorService authorService;
  
   private final IBookService bookService;
    
     
	private final IPublisherService publisherService;
   
    public BookController(IAuthorService authorService,IBookService bookService,IPublisherService publisherService){
    	this.authorService=authorService;
    	this.bookService=bookService;
    	this.publisherService=publisherService;
    	
    }
    @GetMapping("/")
    public String index(Model model){
    	 
    		model.addAttribute("books",bookService.getAll());
        return "index";
    }
    @GetMapping("/search")
    public String search(Model model){
model.addAttribute("authors", authorService.findAll());
    		
        return "book/search";
    }
    
    @GetMapping("/book/getall")
    public String index(Model model,@CurrnetUser Author author){
    	 
    		model.addAttribute("books",bookService.findByAuthor(author));
    		
        return "book/mybooks";
    }
    @GetMapping("/book/add")
    public String bookadd(Model model){
        model.addAttribute("authors",authorService.findAll());
        model.addAttribute("publishers",publisherService.findAll());
        
        return "book/bookadd";
    } 
    
    @GetMapping("/book/edit/{bookId}")
    public String bookedit(Model model,@PathVariable(value = "bookId") Long bookId,@CurrnetUser Author author){
    	if(author.getRole().equals("admin")) {
    		 model.addAttribute("authors",authorService.findAll());
    	}
    	 model.addAttribute("publishers",publisherService.findAll());
        model.addAttribute("book",bookService.findById(bookId));
        return "book/bookedit";
    } 
    
    
    @PostMapping("/book/add")
    public String saveBook(
                           Model model, Book book,   @RequestParam(value = "pub") Long pubid) {
    	
    	bookService.save(book, pubid);
    	model.addAttribute("books",bookService.getAll());
    	return "index";
    }
    
   
    
    @GetMapping("/book/delete/{bookId}")
    public String delete(Model model,@PathVariable(value = "bookId") Long bookId,@CurrnetUser Author author){
    	bookService.delete(bookId);
    	if(author.getRole().equals("admin")) {
    		model.addAttribute("books",bookService.getAll());
    		return "index"; 
    	}
    	else {
    		model.addAttribute("books",bookService.findByAuthor(author));
    		return "book/mybooks";
    	}
    	
        
    } 
    
    @PostMapping("/book/edit")
    public String edit(Model model,
    		Book book, @RequestParam(value = "pub") Long pubid){
    	bookService.edit(book,pubid);
    	model.addAttribute("books",bookService.getAll());
    	 
        return "book/mybooks";
    } 
    @GetMapping("/book/search/name")
    public String nameSearch(Model model,@RequestParam(value = "name") String name){
    		model.addAttribute("books",bookService.findByName(name));
    		return "book/searchbook";
       
    } 
    
    @GetMapping("/book/search/isbn")
    public String isbnSearch(Model model,@RequestParam(value = "isbn") String isbn){
    		model.addAttribute("books",bookService.findByIsbn(isbn));
    		return "book/searchbook";
       
    }
    @GetMapping("/book/search/seri")
    public String seriSearch(Model model,@RequestParam(value = "seri") String seri){
    		model.addAttribute("books",bookService.findBySeri(seri));
    		return "book/searchbook";
       
    }
    @GetMapping("/book/search/author")
    public String authorSearch(Model model,Author author){
    		model.addAttribute("books",bookService.findByAuthor(author));
    		return "book/searchbook";
       
    }
  
        
    
}
