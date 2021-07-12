package com.zg.r2.demo.author;


import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class UniqeUserNameValidator implements ConstraintValidator<UniqeUserName,String> {

    @Autowired
    AuthorRepository authorRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
       if(authorRepository.findByUserName(s)==null){
           return true;
       }
        return false;
    }
}
