package com.zg.r2.demo.author;

import java.util.List;

public interface IAuthorService {
	 public Author save(Author author);
	 List<Author> findAll();
	 Author findByUserName(String userName);
	 boolean delete(long id,Author author);
}
