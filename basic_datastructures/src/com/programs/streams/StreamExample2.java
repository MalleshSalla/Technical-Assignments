package com.programs.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamExample2 {

	public static void main(String[] args) {

		// map
		List<Integer> number = Arrays.asList(2, 3, 4, 5);
		number.stream().map(x -> 2 * x).forEach(System.out::println);

		// filter
		List<String> names = Arrays.asList("Reflection", "Collection", "Stream", "Salla");
		names.stream().filter(s -> s.startsWith("S")).forEach(System.out::println);

		// sorted
		List<String> names1 = Arrays.asList("Reflection", "Collection", "Stream");
		List<String> result = names1.stream().sorted().collect(Collectors.toList());
		result.forEach(System.out::println);

		// collect
		List<Integer> number1 = Arrays.asList(2, 3, 4, 5, 3);
		Set<Integer> square = number1.stream().map(x -> x * x).collect(Collectors.toSet());
		square.forEach(System.out::println);

		System.out.println("_________");
		List<Integer> number2 = Arrays.asList(2, 3, 4, 5);
		number2.stream().map(x -> x * x).forEach(y -> System.out.println(y));

		System.out.println("------------");
		List<Integer> number3 = Arrays.asList(2, 3, 4, 5);
		int even = number3.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
		System.out.println(even);

	}

}
