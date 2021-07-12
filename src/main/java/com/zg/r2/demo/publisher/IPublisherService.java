package com.zg.r2.demo.publisher;

import java.util.List;

public interface IPublisherService {
	public Publisher save(Publisher publisher);
	public List<Publisher> findAll();
	public Publisher findById(long id);
	public void delete(long id);
}
