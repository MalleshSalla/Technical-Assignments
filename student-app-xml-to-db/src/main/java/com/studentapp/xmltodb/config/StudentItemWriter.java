package com.studentapp.xmltodb.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.studentapp.xmltodb.entity.Student;
import com.studentapp.xmltodb.repo.StudentRepository;

public class StudentItemWriter implements ItemWriter<Student> {
	
	@Autowired
	private StudentRepository repository;

	@Override
	public void write(List<? extends Student> list) throws Exception {
		System.out.println("Writer Thread "+Thread.currentThread().getName());
        repository.saveAll(list);
		
	}

}
