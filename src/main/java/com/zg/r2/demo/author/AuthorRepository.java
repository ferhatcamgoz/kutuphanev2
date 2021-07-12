package com.zg.r2.demo.author;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByUserName(String userName);
    
    Optional<Author> findById(Long id);
    
    int countByUserName(String userName);
}
