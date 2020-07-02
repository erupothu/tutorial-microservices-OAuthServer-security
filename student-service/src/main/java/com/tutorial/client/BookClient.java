package com.tutorial.client;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tutorial.config.BookClientFallback;
import com.tutorial.model.BookModel;

@FeignClient(name = "BOOKSERVICE", path = "/api/book/", fallback = BookClientFallback.class)
@RibbonClient(name = "BOOKSERVICE")
public interface BookClient {
	
	@GetMapping("port")
	public String getPort();
	
	@GetMapping("books")
	Collection<BookModel> findAll();
	
	@GetMapping("{id}")
	BookModel findById(@PathVariable("id") Integer id);
	
	@PostMapping
	BookModel save(@RequestBody BookModel t);
	
	@PutMapping
	BookModel update(@RequestBody BookModel t);
	
	@DeleteMapping("{id}")
	String deleteById(@PathVariable("id") Integer id);
	
	@DeleteMapping
	String delete(@RequestBody BookModel t);
	
	@GetMapping("/invalid")
	String invalid();

}
