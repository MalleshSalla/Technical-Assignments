package com.programs.list;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Student class representing a student
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + '}';
    }
}

public class StudentListSorting {
    public static void main(String[] args) {
        // Creating an ArrayList of Student objects
        ArrayList<Student> studentList = new ArrayList<>();

        // Adding students to the list
        studentList.add(new Student("Virat", 32));
        studentList.add(new Student("Jadeja", 38));
        studentList.add(new Student("Sachin", 45));
        studentList.add(new Student("dhoni", 42));

//        // Displaying the original student list
//        System.out.println("Original Student List:");
//        displayStudentList(studentList);
//
//        // Sorting students by name using Collections.sort() method
//        Collections.sort(studentList, Comparator.comparing(Student::getName));
//
//        // Displaying the student list after sorting by name
//        System.out.println("\nStudent List after Sorting by Name:");
//        displayStudentList(studentList);
//
//        // Sorting students by age using Collections.sort() method with a custom comparator
//        Collections.sort(studentList, Comparator.comparingInt(Student::getAge));
//
//        // Displaying the student list after sorting by age
//        System.out.println("\nStudent List after Sorting by Age:");
//        displayStudentList(studentList);
        
        
        
        
        // Displaying the original student list
        System.out.println("Original Student List:");
        studentList.forEach(System.out::println);

        // Sorting students by name using Streams
        List<Student> sortedByName = studentList.stream()
                .sorted(Comparator.comparing(Student::getName))
                .toList();

        // Displaying the student list after sorting by name
        System.out.println("\nStudent List after Sorting by Name:");
        sortedByName.forEach(System.out::println);

        // Sorting students by age using Streams
        List<Student> sortedByAge = studentList.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .toList();

        // Displaying the student list after sorting by age
        System.out.println("\nStudent List after Sorting by Age:");
        sortedByAge.forEach(System.out::println);
        
        
    }

    // Helper method to display the student list
    private static void displayStudentList(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}