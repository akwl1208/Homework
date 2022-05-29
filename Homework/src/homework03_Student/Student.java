package homework03_Student;

import java.util.ArrayList;
import java.util.List;

public class Student {
	//필드
	private int grade;
	private int clazz;
	private int num;
	private String name;
	List<Mark> marks = new ArrayList<Mark>(); //학생의 성적
	List<Record> records = new ArrayList<Record>();//학생의 학년, 학기별 평균 및 총점들을 관리
	//생성자
	public Student(int grade, int clazz, int num, String name, List<Mark> marks) {
		setGrade(grade);
		this.clazz = clazz;
		this.num = num;
		this.name = name;
		this.marks = marks;
		calculateRecord();
	}//
	//생성자 -> 학년 반 번호로 학생을 찾기 위해
	public Student(int grade, int clazz, int num) {
		setGrade(grade);
		this.clazz = clazz;
		this.num = num;
	}//
	//생성자
	public Student(int grade, int clazz, int num, String name) {
		setGrade(grade);
		this.clazz = clazz;
		this.num = num;
		this.name = name;
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
		if(grade < 1 || grade > 3)
			throw new RuntimeException("학년은 1~3 사이의 정수 입력"); 
		this.grade = grade;
	}

	public int getClazz() {
		return clazz;
	}

	public void setClazz(int clazz) {
		this.clazz = clazz;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
		calculateRecord();
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
	//성적 리스트 marks에서 성적들을 이용하여 기록(학년, 학기에 따른 성적) 리스트 records에 평균을 계산하는 메소드
	public void calculateRecord() {
		
	}
	
}
//학년, 학기별 평균, 총점(앤 주용하진 않지만)을 구하는 클래스
class Record{
	int year;
	int semester;
	int sum;//총점
	int subjectCount;//과목 수
	double avg;//평균
	
}
class Mark {
	//필드
	private int year, semester; //학년, 학기
	private String subject; //과목
	private int midterm, finals, pA; //중간고사, 기말고사, 수행평가
	
	//생성자
	public Mark(int year, int semester, String subject, int midterm, int finals, int pA) {
		setYear(year);
		setSemester(semester);
		this.subject = subject;
		setMidterm(midterm);
		setFinals(finals);
		setpA(pA);
	}//
	
	//생성자 -> 학년 학기 과목으로 값 비교할 때
	public Mark(int year, int semester, String subject) {
		setYear(year);
		setSemester(semester);
		this.subject = subject;
	}//

	//성적 수정
	public void modifyMark(int midterm, int finals, int pA) {
		setMidterm(midterm);
		setFinals(finals);
		setpA(pA);
	}
	
	//toString
	@Override
	public String toString() {
		return  "\n" + year + "학년 " + semester + "학기 | " + subject + " [" 
				+ "중간 : " + midterm + "점 | 기말 : " + finals + "점 | 수행평가 : " + pA + "점]\n";
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
		Mark other = (Mark) obj;
		if (semester != other.semester)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (year != other.year)
			return false;
		return true;
	}//
	
	//getter, setter
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year < 1 || year > 3)
			throw new RuntimeException("학년은 1~3 사이의 정수 입력"); 
		this.year = year;
	}

	public int getSemester() {		
		return semester;
	}

	public void setSemester(int semester) {
		if(semester < 1 || semester > 2)
			throw new RuntimeException("학기는 1 또는 2 입력");
		this.semester = semester;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getMidterm() {
		return midterm;
	}

	public void setMidterm(int midterm) {
		if(midterm < 0 || midterm > 100)
			throw new RuntimeException("점수는 0~100 사이 정수 입력");
		this.midterm = midterm;
	}

	public int getFinals() {
		return finals;
	}

	public void setFinals(int finals) {
		if(finals < 0 || finals > 100)
			throw new RuntimeException("점수는 0~100 사이 정수 입력");
		this.finals = finals;
	}

	public int getpA() {
		return pA;
	}

	public void setpA(int pA) {
		if(pA < 0 || pA > 100)
			throw new RuntimeException("점수는 0~100 사이 정수 입력");
		this.pA = pA;
	}
}

