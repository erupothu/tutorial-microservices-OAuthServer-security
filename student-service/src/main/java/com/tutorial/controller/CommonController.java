package com.tutorial.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommonController<T> {
	
	@GetMapping("/port")
	public String getPort();
	
	@GetMapping
	ResponseEntity<Collection<T>> findAll();
	
	@GetMapping("{id}")
	ResponseEntity<T> findById(@PathVariable Integer id);
	
	@PostMapping
	ResponseEntity<T> save(@RequestBody T t);
	
	@PutMapping
	ResponseEntity<T> update(@RequestBody T t);
	
	@DeleteMapping("{id}")
	ResponseEntity<String> deleteById(@PathVariable Integer id);
	
	@DeleteMapping
	ResponseEntity<String> delete(@RequestBody T t);
	
	@GetMapping("/invalid")
	ResponseEntity<String> invalid();
	
	@GetMapping("/student-books")
	ResponseEntity<?> booksByStudent();

}
