package com.studentapp.dbtocsv.model;





import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
		
		@Id
		private int id;
		private String name;
		private int marks;
		private int age;
		
}
