package com.tutorial.config;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tutorial.client.BookClient;
import com.tutorial.model.BookModel;

@Component
public class BookClientFallback implements BookClient {

	@Override
	public String getPort() {
		// TODO Auto-generated method stub
		return "inside fallback";
	}

	@Override
	public List<BookModel> findAll() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public BookModel findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookModel save(BookModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookModel update(BookModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(BookModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String invalid() {
		// TODO Auto-generated method stub
		return "inside fallback";
	}
	
	
}
