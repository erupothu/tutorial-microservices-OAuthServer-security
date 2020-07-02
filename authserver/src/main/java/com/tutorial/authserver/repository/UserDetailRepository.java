package com.tutorial.authserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.authserver.entity.User;

public interface UserDetailRepository extends JpaRepository<User,Integer> {
	
	Optional<User> findByUsername(String name);
}
