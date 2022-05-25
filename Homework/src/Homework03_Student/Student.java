package Homework03_Student;

import java.util.*;

public class Student {
	//필드
	private int grade;
	private int clazz;
	private int num;
	private String name;
	List<Mark> marks = new ArrayList<Mark>(); //학생의 성적
	
	//생성자
	public Student(int grade, int clazz, int num, String name, List<Mark> marks) {
		this.grade = grade;
		this.clazz = clazz;
		this.num = num;
		this.name = name;
		this.marks = marks;
	}//
	
	//생성자 -> 학년 반 번호로 학생을 찾기 위해
	public Student(int grade, int clazz, int num) {
		this.grade = grade;
		this.clazz = clazz;
		this.num = num;
	}//
	
	//toString
	@Override
	public String toString() {
		return grade + "학년 " + clazz + "반 " + num + "번 " + name + "\n"
				+ marks + "\n";
	}//
	
	//getter, setter
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getClazz() {
		return clazz;
	}

	public void setClazz(int clazz) {
		this.clazz = clazz;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}//

	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (clazz != other.clazz)
			return false;
		if (grade != other.grade)
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	
}

class Mark {
	//필드: 과목, 학년, 학기, 중간, 기말, 수행
	String subject;
	int year; //학년
	int semester; //1학기, 2학기
	int midterm; //중간고사
	int finals; //기말고사
	int pA; //수행평가 -> performance Assessment
	
	//생성자
	public Mark(String subject, int year, int semester, int midterm, int finals, int pA) {
		this.subject = subject;
		this.year = year;
		this.semester = semester;
		this.midterm = midterm;
		this.finals = finals;
		this.pA = pA;
	}//

	//toString
	@Override
	public String toString() {
		return  "\n" + year + "학년 " + semester + "학기 | " + subject + " | " 
				+ "중간 : " + midterm + "점 | 기말 : " + finals + "점 | 수행평가 : " + pA + "점\n";
	}//
	
	
	
	
	

	
	
	
	
}

