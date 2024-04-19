package com.studentapp.csvtocsv.processer;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.studentapp.csvtocsv.entity.Student;
import com.studentapp.csvtocsv.writer.Previous_Writer;

@Component
public class UniqueStudentProcessor implements ItemProcessor< Student,Student> {

	@Override
	public Student process(Student item) throws Exception {
		Map<Integer,Student> previous_Map=Previous_Writer.Previous_Map;
		
		
		if(previous_Map.containsKey(item.getStudent_Id())) {
			//System.out.println(item);
			return null;
		}
		else {
			
			
			return item;
		}
	}
   

}