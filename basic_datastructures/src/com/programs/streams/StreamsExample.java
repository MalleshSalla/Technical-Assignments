package com.programs.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExample {

	public static void main(String[] args) {
		List<Fruit> fruit = new ArrayList<Fruit>();
		fruit.add(new Fruit("Apple", 120, 30, "Red"));
		fruit.add(new Fruit("Banana", 40, 10, "Yellow"));
		fruit.add(new Fruit("Grapes", 60, 50, "Green"));
		fruit.add(new Fruit("Straberry", 80, 10, "Red"));
		fruit.add(new Fruit("Papaya", 110, 30, "Yellow"));

		List<Fruit> list = fruit.stream().filter(c -> c.calories > 80 && c.calories <= 120)
				.collect(Collectors.toList());
		for (Fruit s : list) {
			System.out.println(s.getName()); // Apple, Papaya
											

		}
		System.out.println("---------");

		// filter the list to get only the fruits with less than 100 calories
		List<Fruit> lowCalorieFruits = fruit.stream().filter(s -> s.getCalories() < 100).collect(Collectors.toList());

		// sort the list in descending order of calories
		Collections.sort(lowCalorieFruits, (f1, f2) -> f2.getCalories() - f1.getCalories());

		// print the names of the fruits
		for (Fruit fruit1 : lowCalorieFruits) {
			System.out.println(fruit1.getName());
		}

		/*
		 *
		 * This code first filters the list of fruits to get only the ones with low
		 * calories (less than 100), then it sorts the list in descending order of
		 * calories using the Collections.sort method, and finally it prints the names
		 * of the fruits.
		 */

		System.out.println("--------");

		System.out.println("displaying color wise fruits");
		System.out.println("--------");

		Collections.sort(fruit, (f1, f2) -> f1.getColor().compareTo(f2.getColor()));

		// print the names of the fruits

		for (Fruit fruits : fruit) {
			System.out.println(fruits.getName() + " (" + fruits.getColor() + ")");
		}
		System.out.println("--------");

		List<String> coclorList = fruit.stream().map(m -> m.name).collect(Collectors.toList());
		for (Iterator<String> iterator = coclorList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}

		System.out.println("-------");
		System.out.println("------------ Assingment 3 -----");
		System.out.println("displaying only red color fruits  as per their price in descending order");

		List<Fruit> fruits1 = new ArrayList<>();
		fruits1.addAll(fruit);

		fruits1.stream().filter(s -> s.color.equals("Red")).sorted(Comparator.comparingInt(Fruit::getPrice).reversed())
				.forEach(System.out::println);

		fruit.stream().filter(s -> s.color.equals("Red")).sorted((s1, s2) -> s2.getPrice() - s1.getPrice())
				.forEach(System.out::println);

	}

}
