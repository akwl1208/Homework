package homework03_Student;

import java.util.*;
import java.util.function.Predicate;

import homework02_PostProgram.ConsoleProgram;

public class StudentProgramManager implements ConsoleProgram{
	Scanner scan = new Scanner(System.in);
	private int exitmenu = 7;
	List<Student> list = new ArrayList<Student>();
	
	public StudentProgramManager(Scanner scan) {	
		this.scan = scan;
		//테스트용
		List<Mark> m1 = new ArrayList<Mark>();
		m1.add(new Mark(3, 2, "국어", 100, 90, 80));
		m1.add(new Mark(3, 2, "국어", 70, 60, 50));
		list.add(new Student(3, 2, 1, "짱구", m1));
		List<Mark> m2 = new ArrayList<Mark>();
		m2.add(new Mark(3, 1, "국어", 40, 30, 20));
		list.add(new Student(3, 2, 2, "짱아", m2));
		List<Mark> m3 = new ArrayList<Mark>();
		m3.add(new Mark(2, 1, "국어", 10, 0, 10));
		m3.add(new Mark(2, 2, "국어", 20, 30, 40));
		list.add(new Student(2, 2, 1, "훈이", m3));
		List<Mark> m4 = new ArrayList<Mark>();
		m4.add(new Mark(2, 1, "국어", 50, 60, 70));
		list.add(new Student(2, 1, 1, "맹구", m4));
		List<Mark> m5 = new ArrayList<Mark>();
		m5.add(new Mark(1, 1, "국어", 80, 90, 100));
		m5.add(new Mark(1, 1, "수학", 90, 80, 70));
		list.add(new Student(1, 2, 2, "철수", m5));
		List<Mark> m6 = new ArrayList<Mark>();
		list.add(new Student(1, 1, 1, "유리", m6));
	}

	@Override
	public int selectMenu(Scanner scan) {
		System.out.println("*****학생 관리 프로그램*****");
		System.out.println("1. 학생 정보 등록");
		System.out.println("2. 학생 성적 추가");
		System.out.println("3. 학생 정보 검색");
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
			//있으면 동일한 학생 정보 있다고 알려줌
			if(registerStudent()) //기능2
				printMessage("동일한 학생 정보가 있습니다");
			else //없으면 등록하고 등록됬다고 알려주기
				printMessage("등록됬습니다. 성적 추가 메뉴에서 성적을 추가해주세요");
			break;		
		case 2: //학생 성적 추가
			addMarks(); //기능4
			break;		
		case 3: //학생 정보 검색
			searchStudentInformation(); //기능5
			break;		
		case 4: //학생 성적 수정
			modifyMark(); //기능6
			break;		
		case 5: //학생 성적 삭제
			deleteMark(); //기능8
			break;
		case 6: //학생 정보 삭제
			deleteStudent(); //기능9
			break;
		case 7: 
			for(int i = 0; i <= list.size(); i++) {
			
			}
			break;
		default: printMessage("잘못 입력했습니다. 다시 입력해주세요");	
		}	
	}

	@Override
	public void run() {
		int menu = -1;
		do {
			try {
				menu = selectMenu(scan);
				if(menu != 1 && menu != 7 && list.isEmpty())
					throw new NullPointerException();
				excute(menu);
			}catch (NullPointerException e) {
				printMessage("등록된 학생이 없습니다. 학생 정보를 등록해주세요");
			}catch (InputMismatchException e) {
				printMessage("잘못 입력했습니다");
				scan.nextLine();
			}catch (RuntimeException e) {
				printMessage(e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				printMessage(e.getMessage());
				e.printStackTrace();
				scan.nextLine();
			}			
		}while(menu != exitmenu);
		printMessage("프로그램을 종료합니다");
	}

	//기능1)
	private void printMessage(String str) {
		System.out.println("***********************");
		System.out.println(str);
		System.out.println("***********************");
	}//printMessage
	
	//기능2) 학년 반 번호 이름을 입력받아 학생 정보 등록
	private boolean registerStudent() {
		//학생 정보 입력
		printMessage("등록할 학생 정보를 입력하세요");
		System.out.print("학년 반 번호 이름 : ");
		int grade = scan.nextInt();
		int clazz = scan.nextInt();
		int num = scan.nextInt();
		String name = scan.next();
		//동일한 학생 정보가 있는지 확인
		if(list.contains(new Student(grade, clazz, num)))
			return true;
		else { //없으면 list에 정보 추가
			list.add(new Student(grade, clazz, num, name));
			return false;
		}
	}//registerStudent
	
	//기능3)학년 반 번호을 입력받으면 list의 인덱스 찾기 
	private int findIndex() {
		System.out.print("학년 반 번호 : ");
		int grade = scan.nextInt();
		int clazz = scan.nextInt();
		int num = scan.nextInt();
		//해당 학생의 index 찾기
		return list.indexOf(new Student(grade, clazz, num));
	}//findIndex
	
	//기능4) 학생 정보를 입력받고 일치하는 학생이 있으면 성적을 입력받아 추가
	private void addMarks() {
		//학생 정보 입력
		printMessage("성적 추가할 학생 정보를 입력하세요");
		int index = findIndex(); //기능3
		//동일한 학생 정보가 있으면(index가 0보다 크거나 같으면) 성적 입력
		if(index >= 0) {
			//학생의 성적 리스트 가져오고
			List<Mark> marks = list.get(index).getMarks();
			while(true) {
				printMessage(list.get(index).getName() + " 학생의 성적을 추가합니다");
				Mark tmp = inputMark();
				//동일한 성적 정보가 있는지 확인 -> 있으면 동일한 성적 정보가 있다고 알려줌
				if(marks.contains(tmp))
					printMessage("이미 등록된 성적 정보입니다");
				else {//없으면 성적 입력
					System.out.print("중간 기말 수행평가 : ");
					int midterm = scan.nextInt();
					int finals = scan.nextInt();
					int pA = scan.nextInt();
					//성적 리스트에 저장
					tmp.modifyMark(midterm, finals, pA);
					marks.add(tmp);
					//학년 학기 과목 순으로 오름차순 정렬
					marks.sort(new Comparator<Mark>(){
						@Override
						public int compare(Mark o1, Mark o2) {
							if(o1.getYear() != o2.getYear())
								return o1.getYear() - o2.getYear();
							if(o1.getSemester() != o2.getSemester())
								return o1.getSemester() - o2.getSemester();	
							return o1.getSubject().compareTo(o2.getSubject());
						}
					});
					printMessage("성적이 추가되었습니다");
				}
				//성적 더 추가할지 묻고 아니면 메뉴로 돌아가기
				System.out.print("성적을 더 추가하려면 1, 메뉴로 돌아가려면 아무키나 누르세요 : ");
				if(!scan.next().equals("1")) 
					break;
			}	
		}else //없으면 학생 정보가 없다고 알려주기 
			printMessage("등록된 학생 정보가 없습니다. 학생 정보 등록해주세요");
	}//addMarks
	
	//기능5) 전체, 학년별, 반별, 학생별 학생정보 검색
	private void searchStudentInformation() {
		//학년 반 번호 순으로 오름차순 정렬
		list.sort(new Comparator<Student>(){
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getGrade() != o2.getGrade()) 
					return o1.getGrade() - o2.getGrade();
				if(o1.getClazz() != o2.getClazz()) 
					return o1.getClazz() - o2.getClazz();
				return o1.getNum() - o2.getNum();
			}
		});
		//메뉴 출력
		System.out.println("*******학생 정보 검색*******");
		System.out.println("1. 전체 학생 정보");
		System.out.println("2. 학년별 학생 정보");
		System.out.println("3. 반별 학생 정보");
		System.out.println("4. 학생별 학생 정보");
		System.out.println("***********************");
		System.out.print("메뉴 선택 : ");
		int menu = scan.nextInt();
		System.out.println("***********************");
		
		switch(menu) {
		case 1://전체 학생 정보
			printStudentList(s->true); //기능11
			break;
		case 2: //학년별 학생 정보		
			System.out.print("학년 입력 : ");
			int grade = scan.nextInt();
			printStudentList(s->s.getGrade() == grade);
			break;
		case 3: //반별 학생 정보
			System.out.print("학년 반 입력 : ");
			grade = scan.nextInt();
			int clazz = scan.nextInt();
			printStudentList(s->s.getGrade() == grade && s.getClazz() == clazz);
			break;
		case 4: //학생별 
			System.out.print("학년 반 번호 입력 : ");
			grade = scan.nextInt();
			clazz = scan.nextInt();
			int num = scan.nextInt();
			printStudentList(s->s.getGrade() == grade && s.getClazz() == clazz && s.getNum() == num);
			break;
		default: printMessage("잘못 입력했습니다");
		}
	}//searchStudentInformation
	
	//기능6) 학생 성적 수정
	private void modifyMark() {
		//학년 반 번호 입력 후, index 찾기
		printMessage("성적 수정할 학생 정보를 입력하세요");
		int index = findIndex(); //기능3
		//동일한 학생 정보가 있으면(index가 0보다 크거나 같으면) 성적 수정
		if(index >= 0) {
			//학생의 성적 리스트 가져오고 
			List<Mark> marks = list.get(index).getMarks();
			printMessage(list.get(index).getName() + " 학생의 성적을 수정합니다");
			//학년 학기 과목 입력 후 markIndex 찾기
			int markIndex = findmarkIndex(marks); //기능7
			//동일한 성적 정보가 있는지 확인 -> 있으면 성적 수정
			if(markIndex >= 0) {
				System.out.print("중간 기말 수행평가 : ");
				int midterm = scan.nextInt();
				int finals = scan.nextInt();
				int pA = scan.nextInt();
				//성적 수정
				marks.get(markIndex).modifyMark(midterm, finals, pA);
				printMessage("성적이 수정되었습니다");
			}else
				printMessage("등록되지 않은 성적입니다");
		}else //없으면 학생 정보가 없다고 알려주기 
			printMessage("등록된 학생 정보가 없습니다. 학생 정보 등록해주세요");
	}//modifyMark
	
	//기능7) 학년 학기 과목을 입력받아 markIndex 찾기
	private int findmarkIndex(List<Mark> marks) {
		return marks.indexOf(inputMark());
	}//findmarkIndex
	
	//기능8) 성적 삭제
	private void deleteMark() {
		//학년 반 번호 입력 후, index 찾기
		printMessage("성적 삭제할 학생 정보를 입력하세요");
		int index = findIndex(); //기능3
		//동일한 학생 정보가 있으면(index가 0보다 크거나 같으면) 성적 입력
		if(index >= 0) {
			//학생의 성적 리스트 가져오고
			List<Mark> marks = list.get(index).getMarks();
			printMessage(list.get(index).getName() + " 학생의 성적을 삭제합니다");
			//학년 학기 과목을 입력받아 객체를 생성하여 일치하는게 있으면 삭제하여 true로 돌려줌
			if(marks.remove(inputMark())) {
				printMessage("성적이 삭제되었습니다");
			}else
				printMessage("등록되지 않은 성적입니다");
		}else //없으면 학생 정보가 없다고 알려주기 
			printMessage("등록된 학생 정보가 없습니다. 학생 정보 등록해주세요");
	}//deleteMark
	
	//기능9) 학생 정보 삭제
	private void deleteStudent() {
		//학년 반 번호 입력 후, index 찾기
		printMessage("삭제할 학생 정보를 입력하세요");
		int index = findIndex(); //기능3
		//동일한 학생 정보가 있으면(index가 0보다 크거나 같으면) 삭제할건지 묻기
		if(index >= 0) {
			Student tmp = list.get(index);
			System.out.println(tmp.getGrade() + "학년 " + tmp.getClazz() + "반 " + tmp.getNum() + "번 "
					+ tmp.getName() + " 학생의 정보를 삭제하겠습니까?[예(1)/아니오(2)] : ");
			int ok = scan.nextInt();
			switch(ok) {
			case 1 : 
				list.remove(index);
				printMessage("학생 정보가 삭제되었습니다");
				break;
			case 2 : 
				printMessage("삭제가 취소됩니다");
				break;
			default : printMessage("잘못 입력했습니다");
			}
		}else //없으면 학생 정보가 없다고 알려주기 
			printMessage("등록된 학생 정보가 없습니다. 학생 정보 등록해주세요");
	}//deleteStudent
	
	//기능10) 학년 학기 과목을 입력하면 Mark 객체에 값을 넣고 Mark를 리턴함
	private Mark inputMark() {
		System.out.print("학년 학기 과목 : ");
		int year = scan.nextInt();
		int semester = scan.nextInt();
		String subject = scan.next();
		return new Mark(year, semester, subject);
	}//inputMark
	
	//기능11)학생정보 출력
	private void printStudentList(Predicate<Student> p) {
		List<Student> tmpList = new ArrayList<Student>();	
		for(Student tmp : list) {
			//tmp가 p조건에 맞으면 tmplist에 넣기
			if(p.test(tmp))
				tmpList.add(tmp);
		}
		if(tmpList.size() == 0)
			printMessage("등록된 학생 정보가 없습니다. 학생 정보 등록해주세요");
		else {
			for(Student tmp : tmpList) {
				System.out.println(tmp);
			} //이건 기능12에서 방법1할 때 필요
			System.out.println("***********************");
			printMarkList(tmpList); //기능12
			System.out.println("***********************");
		}
	}//printStudentList
	
	//기능12) 학생들의 학년 학기별 성적을 계산하고 출력
	private void printMarkList(List<Student> tmp) {	
		//방법1) 전체 학년별 반별 학생별 학생들의 성적의 총점과 평균 계산
		List<Student> std = new ArrayList<Student>(); //임시 학생 리스트 생성
		List<Mark> m = new ArrayList<Mark>(); //임시 성적 리스트 생성
		for(int j = 0; j < tmp.size(); j++) {		
			for(int i = 0; i < tmp.get(j).getMarks().size(); i++) {			
				m.add(tmp.get(j).getMarks().get(i)); //성적 리스트에 담음			
			}
		}
		std.add(new Student(m)); //성적 리스트를 학생 리스트에 담음
		System.out.println(std.get(0).getRecords());
		
		//방법2_ 각각 학생의 성적을 출력하고 점수가 있는 학기만 학기 총점과 평균을 출력
		/*for(int i = 0; i<tmp.size(); i++) {
			System.out.println(tmp.get(i));
			for(int j = 0; j<tmp.get(i).getRecords().size(); j++) {		
				if(tmp.get(i).getRecords().get(j).sum != 0)
					System.out.println(tmp.get(i).getRecords().get(j));
			}
		}*/
	}//printMarkList
}
