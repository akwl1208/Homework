package homework04_PhoneBook;

import java.util.*;

public class PhoneBook {
//전화번호 관리 프로그램
	public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int menu = 0;
	int exitMenu = 4; 
	Map<String, String> pb = new HashMap<String, String>(); //전화번호부
	
	//테스트용
	pb.put("홍길동", "010-1111-1111");
	pb.put("김철수", "010-2222-2222");
	pb.put("김영희", "010-3333-3333");
	
	do {//메뉴 출력
		System.out.println("******메뉴******");
		System.out.println("1. 전화번호 추가");
		System.out.println("2. 전화번호 확인");
		System.out.println("3. 전화번호 검색");
		System.out.println("4. 프로그램 종료");
		System.out.println("**************");
		System.out.print("메뉴 입력 : ");
		menu = scan.nextInt();
		
		switch(menu) {
		case 1: //전화번호 추가
			//이름 전화번호 입력
			System.out.print("이름 : ");
			String name = scan.next();
			//전화번호부에 똑같은 이름이 없으면 전화번호 입력
			if(!pb.containsKey(name)) {
				System.out.print("전화번호[예: 010-1234-5678] : ");
				String number = scan.next();
				//똑같은 전화번호가 아니면 전화번호부에 저장
				if(!pb.containsValue(number)) {
					pb.put(name, number);
				}else {
					System.out.println("이미 등록된 전화번호입니다");
				}
			}else {
				System.out.println("이미 등록된 이름입니다");
			}
			break;
			
		case 2: //전화번호 확인
			if(pb.isEmpty()) {
				System.out.println("등록된 전화번호가 없습니다");
			}
			Iterator<String> it = pb.keySet().iterator();
			while(it.hasNext()) {
				name = it.next();
				String number = pb.get(name);
				System.out.println(new Person(name, number));
			}
			break;
			
		case 3: //전화번호 검색
			System.out.print("이름 : ");
			name = scan.next();
			//이름이 있으면 전화번호 출력
			if(pb.containsKey(name)) {
				System.out.println(pb.get(name));	
			}else {
				System.out.println("전화번호가 등록되지 않은 사람입니다");
			}
			break;
			
		case 4: System.out.println("프로그램을 종료합니다");
			break;
			
		default: System.out.println("잘못 입력했습니다. 다시 입력하세요");
		}
	}while(menu != exitMenu);

	}

}

class Person{
	String name, number;

	public Person(String name, String number) {
		this.name = name;
		this.number = number;
	}

	@Override
	public String toString() {
		return name + " | " + number;
	}	
}

