package com.tutorial.authserver.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth/")
public class WelcomeController {
	
	@GetMapping(value="welcome")
	public ResponseEntity<?> welcome() {
		
		return ResponseEntity.ok("welcome to Authentication server");
	}
	
	@GetMapping(value="/me")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

}
