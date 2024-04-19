package com.programs.set;

import java.util.HashSet;
import java.util.Set;

public class SetExample1 {
	
	public static void main(String[] args) {
		
		Set<String> linkedHashSet = new HashSet<>();
		
		// Adding elements to LinkedHashSet
		
        linkedHashSet.add("Apple");
        linkedHashSet.add("Banana");
        linkedHashSet.add("Orange");
        linkedHashSet.add("Grapes");

        // Displaying elements in the order of insertion
        System.out.println("LinkedHashSet elements: " + linkedHashSet);
		
	}

}
