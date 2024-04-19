package com.programs.list;

import java.util.ArrayList;
import java.util.List;

public class ListExample2 {

	public static void main(String[] args) {
		List<String> fruits = new ArrayList<>();

		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Orange");

		System.out.println("Fruits: " + fruits);

		fruits.remove(1); //based on index

		System.out.println("Fruits: " + fruits);

		for (String fruit : fruits) {
			System.out.println(fruit);
		}

		int size = fruits.size();
		System.out.println(size);

		boolean found = fruits.contains("Orange");

		System.out.println("Is Orange  in the list? " + found);

		fruits.clear();

		System.out.println("fruits after clearing: " + fruits);

		boolean isEmpty = fruits.isEmpty();

		System.out.println("Is fruits ArrayList empty now? " + isEmpty);

	
	}

}
