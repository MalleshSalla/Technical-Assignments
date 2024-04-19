package com.programs.map;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapExample1 {
	
	public static void main(String[] args) {
		
		Map<Integer, String> courseMap = new HashMap<>();

		courseMap.put(1, "C");
		courseMap.put(2, "C++");
		courseMap.put(3, "Java");
		courseMap.put(4, "Spring boot");

		//Iterating using entrySet() 
		System.out.println("-------------using entrySet()");
		Iterator<Entry<Integer, String>> iterator1 = courseMap.entrySet().iterator();

		while (iterator1.hasNext()) {
			Entry<Integer, String> entry = iterator1.next();
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}

}
