package com.zg.r2.demo.publisher;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.zg.r2.demo.book.Book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	 @OneToMany(fetch = FetchType.EAGER,mappedBy = "publisher",cascade = CascadeType.REMOVE)
	 private List<Book> bookList;
	
	public Publisher(String name){
		this.name=name;
	}

	public Publisher() {
		
	}
	
	
	
}
