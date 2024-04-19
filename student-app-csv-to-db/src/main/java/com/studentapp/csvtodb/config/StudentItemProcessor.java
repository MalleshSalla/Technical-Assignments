package com.studentapp.csvtodb.config;

import org.springframework.batch.item.ItemProcessor;

import com.studentapp.csvtodb.model.Student;

public class StudentItemProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) throws Exception {
    	
    	if(student.getMarks()>75) {
    		return student;
    	}else {
    		 return null;
    	}
    	
       
    }
    
}
