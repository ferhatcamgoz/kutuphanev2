package com.zg.r2.demo;

import com.zg.r2.demo.author.Author;
import com.zg.r2.demo.author.AuthorRepository;
import com.zg.r2.demo.book.Book;
import com.zg.r2.demo.book.BookRepository;
import com.zg.r2.demo.publisher.Publisher;
import com.zg.r2.demo.publisher.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);   
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
    @Bean
    CommandLineRunner createInitialUsers( AuthorRepository authorRepository,BookRepository bookRepository,PublisherRepository publisherRepository){
        return (args)->{
          List<Publisher> publishers = new ArrayList<>();
        	for(int i =0;i<5;i++) {
        		publishers.add( publisherRepository.save(new Publisher(i+". yayın evi")));
        	}
        	for(int i =1;i<=5;i++){
                Author author= new Author("a"+i, new BCryptPasswordEncoder().encode("1"));
                if(i%2==0) {
                	author.setRole("admin");           
                }
                else {
                	author.setRole("user");
                }
                authorRepository.save(author);
                
                for(int k =0;k<2;k++) { 
            
                	Book book = new Book();
                	book.setAuthor(author);
                	book.setPublisher(publishers.get(k));
                	book.setName(i+". yazarın "+ k+".kitabı ");
                	book.setDescription("description");
                	book.setIsbn("isbn");
                	book.setSeriname("seriname");
                	book.setSubname("subname");
                
                	bookRepository.save(book);
                }

            }

        };
    }
}
