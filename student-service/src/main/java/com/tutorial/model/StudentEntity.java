package com.tutorial.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class StudentEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private String name;

	private String studentId;

	private String courseId;

	private String[] bookIds;
	
	@Transient
	private List<BookModel> books;
	
	private Date createdDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<BookModel> getBooks() {
		return books;
	}

	public void setBooks(List<BookModel> books) {
		this.books = books;
	}

	public String[] getBookIds() {
		return bookIds;
	}

	public void setBookIds(String[] bookIds) {
		this.bookIds = bookIds;
	}
	
	
	
}
