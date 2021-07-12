package com.zg.r2.demo.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService implements IAuthorService {


    
    private final AuthorRepository authorRepository;
     
    private final PasswordEncoder passwordEncoder;
    
    public AuthorService(AuthorRepository authorRepository,PasswordEncoder passwordEncoder) {
    	this.authorRepository=authorRepository;
    	this.passwordEncoder=passwordEncoder;
    }

    public Author save(Author author){
    	author.setPassword(passwordEncoder.encode(author.getPassword()));
        return authorRepository.save(author);
    } 

    public List<Author> findAll(){

        return authorRepository.findAll();
    }
    
    public Author findByUserName(String userName){

        return authorRepository.findByUserName(userName);
    }
    public boolean delete(long id,Author author){
    	Author author2= authorRepository.findById(id).get();
    	if(author2==null||author2.getId()!=author.getId()) {
    		return false;
    	}
    	else {
    		 authorRepository.deleteById(id);
    		 return true;
    	}
        
    }
}
