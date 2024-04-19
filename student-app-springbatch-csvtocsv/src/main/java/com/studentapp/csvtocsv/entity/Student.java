package com.studentapp.csvtocsv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	   private int student_Id;
	   private String student_Name;
	   private String subject;
	   private int marks;
	   public static String[] getFields() {
		   return new String[] {"student_Id","student_Name","subject","marks"};
	   }

}
