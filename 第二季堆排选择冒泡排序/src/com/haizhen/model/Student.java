package com.haizhen.model;

public class Student implements Comparable<Student> {

	// 年龄
	public int age;
	// 分数
	public int score;

	public Student(int age, int score) {
		this.age = age;
		this.score = score;
	}

	@Override
	public int compareTo(Student o) {
		return this.age - o.age;
	}

}
