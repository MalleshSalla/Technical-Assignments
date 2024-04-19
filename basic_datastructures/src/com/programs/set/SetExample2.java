package com.programs.set;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetExample2 {
	
	public static void main(String[] args) {
		  Set<Integer> linkedHashSet = new LinkedHashSet<>();

	        // Adding elements to LinkedHashSet
	        linkedHashSet.add(10);
	        linkedHashSet.add(5);
	        linkedHashSet.add(20);
	        linkedHashSet.add(15);
	        linkedHashSet.add(25);
	        
	       
	        System.out.println("LinkedHashSet elements: " + linkedHashSet);

	       
	        System.out.print("LinkedHashSet elements using iterator: ");
	        Iterator<Integer> iterator = linkedHashSet.iterator();
	        while (iterator.hasNext()) {
	            System.out.print(iterator.next() + " ");
	        }
	        System.out.println();

	        linkedHashSet.removeIf(element -> element > 15);
	        System.out.println("LinkedHashSet after removeIf: " + linkedHashSet);

	        boolean containsElement = linkedHashSet.contains(10);
	        System.out.println("LinkedHashSet contains 10: " + containsElement);

	        boolean isEmpty = linkedHashSet.isEmpty();
	        System.out.println("Is LinkedHashSet empty? " + isEmpty);

	        linkedHashSet.clear();
	        System.out.println("LinkedHashSet after clearing all elements: " + linkedHashSet);
	}

}
