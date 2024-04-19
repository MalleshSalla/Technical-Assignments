package com.studentapp.xmltodb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapp.xmltodb.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
