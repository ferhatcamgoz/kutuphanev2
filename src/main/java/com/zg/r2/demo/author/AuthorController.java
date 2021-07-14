package com.zg.r2.demo.author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthorController {

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    private String login(){
        return "login";
    }
    
  
    @GetMapping("/registration")
    private String register(){
        return "registration";
    } 
    
    @GetMapping("/author/edit")
    public String edit(Model model,@CurrnetUser Author author){
    	model.addAttribute("author", author);
        return "author/editauthor";
    }
    @PostMapping("/save-author")
    public String saveAuthor( @Valid Author author,
    		BindingResult result,Model model) {
    	if (result.hasErrors()) {
    		
    		 model.addAttribute("errors",  result.getAllErrors());
    	    return "registration";
    	  } 
            authorService.save(author); 
            model.addAttribute("author", author);

        return "login";
    }
    
    
    @RequestMapping(value="/delete-author/{authorid}", method = RequestMethod.POST)
    public String deleteAuthor (
    		@PathVariable(value = "authorid") Long authorid,@CurrnetUser Author author, HttpServletResponse response) {
    	if(authorService.delete(authorid,author)) {
    		
    		return "redirect:/logout";
    		 
    		
    	}
    	else {
    		response.setStatus(403);
    		return "author/editauthor";
    	}
        
    	
    }
    
    
    @PostMapping("/author/edit")
    public String postedit(
                             Model model, @Valid AuthorUpdate author,
                            
                     		BindingResult result,@CurrnetUser Author author2) {
    	
    	 if(!author2.getUsername().equals(author.getName())) {
    		 
    		
    		 result.addError(new ObjectError("access", "Yetkisiz Erişim"));
    		 model.addAttribute("errors",  result.getAllErrors());
    		 
    		 return "author/editauthor";
    	 } 
    	if (result.hasErrors()) {
    		 
   		 model.addAttribute("errors",  result.getAllErrors());
   	    return "author/editauthor";
   	  } 
    	
            authorService.save(new Author(author2.getId(), author2.getUsername(),author.getPassword(),author.getRole()));
           
            model.addAttribute("author", author);
            
        return "redirect:/logout";
    }
    
    

    @GetMapping("/getAll")
    public List<Author> authorList(){
        return authorService.findAll();
    }


}
