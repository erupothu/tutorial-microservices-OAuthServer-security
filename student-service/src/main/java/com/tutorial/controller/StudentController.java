package com.tutorial.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.MethodInvocationRecorder.Recorded.ToCollectionConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tutorial.client.BookClient;
import com.tutorial.model.BookModel;
import com.tutorial.model.StudentEntity;
import com.tutorial.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/student/")
public class StudentController implements CommonController<StudentEntity> {

	@Value("${server.port}")
	private String port;

	Logger log = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private CommonService<StudentEntity> studentService;

	@Autowired
	BookClient bookClient;

	public String getPort() {
		return port;
	}

	public ResponseEntity<Collection<StudentEntity>> findAll() {
		Long startTime = Instant.now().toEpochMilli();
		log.info("studentService Resource findAll");
		Collection<StudentEntity> result = studentService.findAll();
		Long endTime = Instant.now().toEpochMilli();
		Long elapsedTime = endTime - startTime;
		log.info("Time to fectech all Books with cache: " + elapsedTime);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<StudentEntity> findById(Integer id) {
		log.info("studentService Resource find by id");
		Long startTime = Instant.now().toEpochMilli();
		StudentEntity result = studentService.findById(id);
		Long endTime = Instant.now().toEpochMilli();
		Long elapsedTime = endTime - startTime;
		log.info("Time to fectech a Book with cache : " + elapsedTime);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<StudentEntity> save(StudentEntity t) {
		log.info("studentService Resource Save");
		return new ResponseEntity<>(studentService.saveOrUpdate(t), HttpStatus.CREATED);
	}

	public ResponseEntity<StudentEntity> update(StudentEntity t) {
		log.info("studentService Resource update");
		return ResponseEntity.ok(studentService.saveOrUpdate(t));
	}

	public ResponseEntity<String> deleteById(Integer id) {
		log.info("studentService Resource Delete by id");
		return ResponseEntity.ok(studentService.deleteById(id));
	}

	public ResponseEntity<String> delete(StudentEntity t) {
		log.info("studentService Resource Delete");
		return ResponseEntity.ok(studentService.delete(t));
	}

	public ResponseEntity<String> invalid() {
		log.info("studentService Resource invalid");
		return ResponseEntity.ok("invalid request");
	}

	// Call Book Service Client with feign

	private String defaultFallback() {

		return "Fallback";
	}

	@HystrixCommand(fallbackMethod = "defaultFallback")
	public ResponseEntity<?> booksByStudent() {
		log.info("studentService Resource invalid");
		StudentEntity student = studentService.findById(1);
		List<BookModel> books = new ArrayList<BookModel>(bookClient.findAll()); //		bookClient.findAll().stream().collect(Collectors.toList());
		log.info("The Student response received from Book MS= " + books.toString());
		student.setBooks(books);
//		bookClient.findById(1);

		return ResponseEntity.ok(student);
	}

	@GetMapping("loadbalancer")
	private String checkLoadBalancer() {
		String myport =  bookClient.getPort();
		log.info("Book Service Loadbalancer ports:"+ myport);
		return myport;
	}
	
	@GetMapping("circuit-break")
	private String checkCircuitbreak() {
		for(int i=0;i<100;i++) {
			log.info("Book Service fallback LOG"+ bookClient.getPort());
		}
		
		return port;
	}

}
