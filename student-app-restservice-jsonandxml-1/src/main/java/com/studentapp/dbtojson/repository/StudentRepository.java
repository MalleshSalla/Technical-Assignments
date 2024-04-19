package com.studentapp.dbtojson.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapp.dbtojson.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	//public Student findByName(String name);

}
