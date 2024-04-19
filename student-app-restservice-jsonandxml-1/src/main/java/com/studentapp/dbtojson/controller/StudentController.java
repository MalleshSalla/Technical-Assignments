package com.studentapp.dbtojson.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentapp.dbtojson.entity.Student;
import com.studentapp.dbtojson.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@GetMapping(value = "/getAllStudents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Student>> getStudentsData() {

		List<Student> list = repository.findAll();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/getStudentById/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Student> getById(@PathVariable int id) throws Exception {
		Student student = repository.findById(id).orElseThrow(() -> new Exception("student not found id " + id));
		if (student != null) {
			return ResponseEntity.ok(student);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	@GetMapping(value = "/students/name/{name}", produces = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE })
//	public ResponseEntity<Student> getByName(@PathVariable String name) throws Exception {
//		Student student = repository.findByName(name);
//		if (student != null) {
//			return ResponseEntity.ok(student);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
	

	@PostMapping(value = "/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {

		Student savedStudent = repository.save(student);

		return ResponseEntity.ok(savedStudent);

	}

}
