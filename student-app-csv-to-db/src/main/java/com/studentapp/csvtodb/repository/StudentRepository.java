package com.studentapp.csvtodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentapp.csvtodb.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

}
