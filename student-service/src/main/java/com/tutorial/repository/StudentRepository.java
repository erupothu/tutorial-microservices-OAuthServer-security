package com.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.model.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
