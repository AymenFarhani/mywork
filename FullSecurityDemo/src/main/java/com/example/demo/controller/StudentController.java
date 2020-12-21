package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
@RestController
@RequestMapping("/api")
public class StudentController {
	
	public static final List<Student> STUDENTS=Arrays.asList(
			new Student(1,"Spring Boot"),
			new Student(2,"Spring Security"),
			new Student(3,"Spring Starter"));
	
	@GetMapping("/student/{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
				.filter(student -> studentId.equals(student.getId()))
						.findFirst()
						.orElseThrow(() -> new IllegalStateException("Student "+studentId+" does not exist."));
	}

}
