package com.tutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student/")
public class WelcomeCotroller {

	@GetMapping("welcome")
	public ResponseEntity<?> welcome() {
		return ResponseEntity.ok("welcome to books service");
	}
}
