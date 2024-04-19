package com.programs.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListExample1 {

	public static void main(String[] args) {

		List<Integer> num = new ArrayList<Integer>();
		
		num.add(45);
		num.add(32);
		num.add(61);
		num.add(12);
		num.add(86);
		
		
		System.out.println(num);
		Collections.sort(num.reversed());
		System.out.println(num);
		
		List<Integer> sortedNum = num.stream().sorted().collect(Collectors.toList());
		System.out.println(sortedNum);
		
	}

}
