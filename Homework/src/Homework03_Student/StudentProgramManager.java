package Homework03_Student;

import java.util.*;

import Homework02_PostProgram.ConsoleProgram;

public class StudentProgramManager implements ConsoleProgram{
	Scanner scan = new Scanner(System.in);
	private int exitmenu = 7;
	List<Student> list = new ArrayList<Student>();
	
	public StudentProgramManager(Scanner scan) {	
		this.scan = scan;
		list = new ArrayList<Student>();
		//테스트용
		List<Mark> marks = new ArrayList<Mark>();
		marks.add(new Mark("국어", 1, 1, 10, 20, 30));
		marks.add(new Mark("수학", 1, 1, 40, 50, 60));
		marks.add(new Mark("영어", 1, 1, 70, 80, 90));
		list.add(new Student(1, 1, 1, "홍길동", marks));
	}

	@Override
	public int selectMenu(Scanner scan) {
		System.out.println("*****학생 관리 프로그램*****");
		System.out.println("1. 학생 정보 등록");
		System.out.println("2. 학생 정보 검색");
		System.out.println("3. 학생 성적 추가");
		System.out.println("4. 학생 성적 수정");
		System.out.println("5. 학생 성적 삭제");
		System.out.println("6. 학생 정보 삭제");
		System.out.println("7. 프로그램 종료");
		System.out.println("***********************");
		System.out.print("메뉴 선택 : ");
		int menu = scan.nextInt();
		System.out.println("***********************");
		return menu;
	}

	@Override
	public void excute(int menu) {
		switch(menu) {
		case 1: //학생정보 등록
			//학생 정보 입력
			System.out.println("등록할 학생 정보를 입력하세요");
			System.out.print("학년 반 번호 이름 : ");
			int grade = scan.nextInt();
			int clazz = scan.nextInt();
			int num = scan.nextInt();
			String name = scan.next();
			//동일한 학생 정보가 있는지 확인
			if(list.contains(new Student(grade, clazz, num))) {
				System.out.println("동일한 학생정보가 있습니다");
			}else { //없으면 성적 추가
				//성적을 추가하기전에 성적을 추가하겠습니까하고 물은뒤 y를 입력하면 성적을
				//입력받게 하고, 입력을 다 받으면 더 입력하겠습니까하고 물은 뒤 y를 입력하면
				//성적을 추가로 입력, n이면 입력 종료를 해야 합니다.
				//그리고 성적 입력 시 이미 입력된 과목(국어 1학년 1학기)는 이미 입력된 과목이라고 알려줘야합니다.
				//그리고 성적이 입력될때마다 성적 리스트에 추가하고 입력 종료가 되면 성적 리스트를 학생에 추가해줍니다.
				System.out.print("과목 학년 학기 : ");
				String subject = scan.next();
				int year = scan.nextInt();
				int semester = scan.nextInt();
				
				System.out.print("중간 기말 수행평가 : ");
				int midterm = scan.nextInt();
				int finals = scan.nextInt();
				int pA = scan.nextInt();
				
				//성적 리스트를 만듬
				List<Mark> marks = new ArrayList<Mark>(); 
				//성적 리스트에 입력한 값 추가
				marks.add(new Mark(subject, year, semester, midterm, finals, pA));
				//학생 리스트에 정보 등록
				list.add(new Student(grade, clazz, num, name, marks));
				System.out.println("등록되었습니다");
			}
			break;
			
		case 2: //학생 정보 검색
			for(Student tmp : list) {
				System.out.println(tmp);
			}
			break;
		case 3: //학생 성적 추가
			System.out.println("성적을 추가할 학생의 학년 반 번호를 입력하세요");
			System.out.print("학년 반 번호 : ");
			grade = scan.nextInt();
			clazz = scan.nextInt();
			num = scan.nextInt();
			//학년 반 번호가 일치하는 학생이 있으면 추가할 성적 입력. 성적은 여러번 입력받을 수 있게 반복문을 활용해야함.
			if(list.contains(new Student(grade, clazz, num))) {
				//추가하는 과정에서 이미 등록된 과목인지 체크를 해야합니다.
				int index = list.indexOf(new Student(grade, clazz, num));
				System.out.println(list.get(index).getName() + " 학생의 성적을 추가합니다");
				System.out.println("추가할 성적을 입력하세요");
				System.out.print("과목 학년 학기 : ");
				String subject = scan.next();
				int year = scan.nextInt();
				int semester = scan.nextInt();
				
				System.out.print("중간 기말 수행평가 : ");
				int midterm = scan.nextInt();
				int finals = scan.nextInt();
				int pA = scan.nextInt();subject = scan.next();
				
				//성적 추가....막힌다...
				//입력 받은 값을 이용하여 Mark 객체를 생성 한 후
				//검색한 학생 정보에서 성적 리스트를 가져온 후 성적 리스트에 Mark 객체를 추가합니다.
			}else {
				System.out.println("해당 학생은 찾을 수 없습니다");
			}
			
			break;
		case 4:
			break;
		case 5:
			break;
		case 6: 
			break;
		case 7: 
			break;
		default:		
		}
		
	}

	@Override
	public void run() {
		int menu;
		do {
			menu = selectMenu(scan);
			excute(menu);
		}while(menu != exitmenu);
		System.out.println("프로그램을 종료합니다");
	}

}
