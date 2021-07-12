package com.zg.r2.demo.publisher;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PublisherService implements IPublisherService{
	
	private final PublisherRepository publisherRepository;
	
	public PublisherService(PublisherRepository publisherRepository) {
		this.publisherRepository=publisherRepository;
	}
	
	
	public Publisher save(Publisher publisher) {
		return publisherRepository.save(publisher);
	}
	
	public List<Publisher> findAll(){
		return publisherRepository.findAll();
	}
	public Publisher findById(long id){
		return publisherRepository.findById(id).get();
	}
	public void delete(long id){
		publisherRepository.deleteById(id);
	}
}
