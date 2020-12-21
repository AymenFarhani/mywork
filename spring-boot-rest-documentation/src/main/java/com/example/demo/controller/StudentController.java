package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/students")
@Api(description = "This API is for rest student Manager.")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping(value = "/all")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping(value = "/student/{idStudent}")
	public ResponseEntity<Student> getStudentById(@PathVariable("idStudent") Integer idStudent) {
		return ResponseEntity.ok(studentRepository.findById(idStudent).get());
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Student> createStudent(@RequestBody @Validated Student student) {
		return ResponseEntity.ok(studentRepository.save(student));
	}

	@DeleteMapping(value = "/delete/{idStudent}")
	public void deleteStudent(@PathVariable("idStudent") Integer idStudent) {
		 studentRepository.deleteById(idStudent);
	}
}
