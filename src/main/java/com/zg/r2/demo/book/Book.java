package com.zg.r2.demo.book;

import com.zg.r2.demo.author.Author;
import com.zg.r2.demo.publisher.Publisher;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String name;
    
    
    private String description;
    
    private String subname;
    
  
    private String seriname;

   
    private String isbn;

    @ManyToOne
    private Author author;
    
    @ManyToOne
    private Publisher publisher;

}
