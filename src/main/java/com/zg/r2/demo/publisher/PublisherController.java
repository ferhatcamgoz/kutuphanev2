package com.zg.r2.demo.publisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController {
	
	@Autowired
	PublisherService publisherService;
	
	
	@GetMapping("/publisher/add")
	String savePublisher() {
		return "publisher/publisheradd";
	} 
	
	@GetMapping("/publisher/publisherlist")
	String getAll(Model model) {
		model.addAttribute("publishers",publisherService.findAll());
		return "publisher/publisherlist";
	} 
	@GetMapping("/publisher/publisheredit/{id}")
	String edit(Model model,@PathVariable(value = "id") Long id) {
		model.addAttribute("publisher",publisherService.findById(id));
		return "publisher/publisheredit";
	} 
	
	
	@PostMapping("/publisher/save")
	String save(Model model,Publisher publisher) {
		 publisherService.save(publisher);
		
		model.addAttribute("publishers",publisherService.findAll());
		return "publisher/publisherlist";
	}
	
	
	
	@GetMapping("/publisher/delete/{id}")
	String delete(Model model,@PathVariable(value = "id") Long id){
		 publisherService.delete(id);
		 model.addAttribute("publishers",publisherService.findAll());
			return "publisher/publisherlist";
	}
	 
}
