package com.tutorial.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tutorial.model.StudentEntity;
import com.tutorial.repository.StudentRepository;

@Service
public class StudentServiceImpl implements CommonService<StudentEntity> {
	
	@Autowired
	StudentRepository studentRepo;

	@CachePut(value = "books")
	public Collection<StudentEntity> findAll() {
		return studentRepo.findAll();
	}

	@CachePut(value = "books", key = "#id")
	public StudentEntity findById(Integer id) {
		return studentRepo.findById(id).isPresent()?studentRepo.findById(id).get():null;
	}

	public StudentEntity saveOrUpdate(StudentEntity t) {
		return studentRepo.save(t);
	}

	public String deleteById(Integer id) {
		studentRepo.deleteById(id);
		return "successful";
	}

	public String delete(StudentEntity t) {
		studentRepo.delete(t);
		return "successful";
	}
	
//	@Scheduled(cron = "*/5 * * * * *") // schedule to run every 15sec
	public void scheduleFetchBook() {
		StudentEntity book = studentRepo.findById(1).isPresent()?studentRepo.findById(1).get():null;
		System.out.println("scheduled get book @"+ System.currentTimeMillis()+", book"+ book);
	}
	
	

}
