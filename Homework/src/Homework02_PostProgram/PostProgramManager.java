package Homework02_PostProgram;

import java.util.*;
//익명 게시판
public class PostProgramManager implements ConsoleProgram {
	Scanner scan = new Scanner(System.in);
	private int exitmenu = 5;
	List<Post> posts = new ArrayList<Post>(); //게시판..
	private String managerPw = "manager"; //공지글을 작성하기 위한 관리자 비밀번호 -> 게시글 비밀번호랑 다름
	
	//생성자
	public PostProgramManager(Scanner scan) {
		this.scan = scan;
		posts = new ArrayList<Post>();
		//테스트용
		posts.add(new Post("[공지] 게시판 작성 주의사항", "바른말 고운말 사용"));	
		posts.add(new Post("[일반] 아지 세젤귀", "아지짱짱", "1234", "반박불가"));
		posts.add(new Post("[일반] 아지 귀여워", "짱짱아지", "abcd", "너무 귀여워"));
	}//

	@Override
	public int selectMenu(Scanner scan) {
		
		System.out.println("**익명게시판에 오신걸 환영합니다**");
		System.out.println("1. 게시글 등록(공지/일반)");
		System.out.println("2. 게시글 보기");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.println("5. 프로그램 종료");
		System.out.println("*************************");
		System.out.print("메뉴 입력 : ");
		int menu = scan.nextInt();
		System.out.println("*************************");

		return menu;
	}

	@Override
	public void excute(int menu) {
		switch(menu) {
		case 1:/* < 요구사항명세서 >
			 1. 게시글 등록
			  - 관리자가 입력하는 공지와 회원이 입력하는 일반 게시글이 구분
			  - 공지글
			  	- 관리자만 등록해야하기 때문에 관리자 비밀번호와 일치하면 게시글을 작성할 수 있도록 함
			  	- 관리자 비밀번호와 제목과 내용만 입력
			  	- 게시글 제목에 [공지]를 붙임
			  	- 게시글 작성일은 자동으로 입력
			  	- 아이디는 "Manager", 비밀번호는 "manager"로 자동 입력
			  	- 제목, 내용은 공백이 포함/나머지는 공백 없이 작성
			  - 일반 게시글
			  	- 제목, 아이디, 비밀번호, 내용 입력
			    - 게시글 제목에 [일반]을 붙임
			    - 게시글 작성일은 자동으로 입력
			    - 아이디, 비밀번호 입력 -> 비밀번호는 나중에 게시글 수정/삭제할 때 게시글을 작성한 회원만 수정가능하도록
			    - 제목, 내용은 공백이 포함/나머지는 공백 없이
			 */
			uploadPost(); //기능4
			break;
			
		case 2: /* < 요구사항명세서 >
			 2. 게시글 보기
			  - 등록된 게시글의 정보 모두 출력 -> 내용 빼고...
			  - 보고싶은 게시글을 선택하면 내용 확인 가능하고, 해당 게시글 조회수 1증가
			  - -1을 누르면 메뉴로 돌아가고, -1을 누르지 않으면 계속 원하는 게시글 보고
			  	게시글 목록이 출력되고 또 원하는 게시글 선택해서 보고...
			 */
			readPost(); //기능6
			break;
			
		case 3:/* < 요구사항명세서 >
			 3. 게시글 수정
			  - 등록된 게시글이 모두 출력 -> 내용 빼고...
			  - 수정하고 싶은 게시물을 선택하면 게시글 비밀번호를 입력해야함
			  - 일치하면 제목과 내용 수정, 일치하지 않으면 수정 못함
			 */
			modifyPost(); //기능9
			break;
			
		case 4:/* < 요구사항명세서 >
			 4. 게시글 삭제
			  - 등록된 게시글이 모두 출력 -> 내용 빼고...
			  - 삭제하고 싶은 게시물을 선택하면 게시글 비밀번호를 입력해야함
			  - 일치하면 게시글 삭제, 일치하지 않으면 삭제 못함
			 */
			deletePost(); //기능10
			break;
		case 5:
			break;
		default: printMessage("잘못 선택했습니다. 다시 선택해주세요");
			
			
		}
		
	}

	@Override
	public void run() {
		int menu;
		do {
			try {
				menu = selectMenu(scan);
				excute(menu);
			} catch (Exception e) {
				menu = -1;
				scan.nextLine();
			}		
		}while(menu != exitmenu);
		printMessage("프로그램을 종료합니다");
		
	}
	
	//기능1) 메세지 출력
	private void printMessage(String str) {
		System.out.println("*************************");
		System.out.println(str);
		System.out.println("*************************");
	}
	
	//기능2) 공지글 작성
	private void writeNotice() {
		try {
			printMessage("공지는 관리자만 등록 가능합니다");
			//비밀번호 입력
			System.out.print("관리자 비밀번호 입력 :");
			String pw = scan.next();
			scan.nextLine();
			//입력한 비밀번호가 관리자 비밀번호가 같다면
			if(pw.equals(managerPw)) {
				//게시글 작성..
				System.out.println("게시글 제목을 입력하세요 : ");
				String title = scan.nextLine();
				System.out.println("내용을 입력하세요 : ");
				String content = scan.nextLine();
				//작성한 내용을 게시판에 올리기
				posts.add(new Post("[공지] " + title, content));
				printMessage("게시글이 등록됬습니다");	
			}else { //비밀번호가 틀리면
				printMessage("비밀번호가 틀렸습니다. 메뉴로 돌아갑니다");
				return;
			}
		}catch (Exception e) {
			System.out.println("메뉴로 돌아갑니다");
			scan.nextLine();
			return;
		}		
	}//writeNotice
	
	//기능3) 게시글 작성
	private void writePost() {
		try {
			//게시글 작성..
			System.out.println("게시글 제목을 입력하세요 : ");
			String title = scan.nextLine();
			System.out.println("아이디를 입력하세요 : ");
			String id = scan.next();
			System.out.println("게시글 비밀번호를 입력하세요 : ");
			String pw = scan.next();
			scan.nextLine();
			System.out.println("내용을 입력하세요 : ");
			String content = scan.nextLine();
			//작성한 내용을 게시판에 올리기
			posts.add(new Post("[일반] " + title, id , pw, content));			
			printMessage("게시글이 등록됬습니다");
		} catch (Exception e) {
			System.out.println("메뉴로 돌아갑니다");
			scan.nextLine();
			return;
		}
	}//writePost
	
	//기능4) 공지/일반 묻고 게시글 올리기
	private void uploadPost() {
		//공지글 등록할건지 일반 게시글 올린건지 묻기
		try {
			System.out.print("게시글을 등록하시겠습니까?[공지(1)/일반(2)] : ");
			int type = scan.nextInt();
			scan.nextLine();
			//게시글 작성
			switch(type) {
			case 1: //공지 작성 및 등록
				writeNotice(); //기능2
				break;
			case 2: //일반 작성 및 등록
				writePost(); //기능3
				break;
			default: printMessage("잘못 선택했습니다. 메뉴로 돌아갑니다");
			}
		} catch (Exception e) {
			System.out.println("메뉴로 돌아갑니다");
			scan.nextLine();
			return;
		}	
	}//uploadPost
	
	//기능5) 게시글 목록 출력
	private void printPostList() {
		//예외 처리
		if(posts == null) {
			printMessage("등록된 게시글이 없습니다");
			return;
		}
		//목록 출력
		System.out.println("********게시글 목록*********");
		for(Post tmp : posts) {
			System.out.println(tmp);
		}
		System.out.println("*************************");
	}//printPostList
	
	//기능6) 게시글 보기
	private void readPost() {
		int num = 0;
		do {
			//게시판 목록 출력
			printPostList(); //기능5
			try {
				//게시글 선택
				System.out.print("게시글 선택[메뉴로 돌아가기: -1] : ");
				num = scan.nextInt();	
				//상세 게시글을 확인
				if(num >= 1) {
					//조회수 증가
					posts.get(num-1).updateView();
					//상세 내용 출력
					posts.get(num-1).printDetail();
				}
				//예외 처리: 입력값이 0이거나 -1보다 작은 경우
				if(num < -1 || num == 0) {
					throw new Exception();
				}
			}catch (Exception e) {
				printMessage("잘못입력했습니다. 다시 입력하세요");
			}
		}while(num != -1);
		printMessage("메뉴로 돌아갑니다");
	}//readPost
	
	//기능7) 게시물 수정
	private void modifyPost() {
		//게시판 목록 출력
		printPostList();	
		try {
			//수정할 게시글 선택
			System.out.print("수정할 게시글 선택 : ");
			int num = scan.nextInt();
			//예외 처리: num이 0보다 작거나 같거나, num이 posts.size보다 클 때
			if(num <= 0 || num > posts.size()) {
				throw new Exception();
			}
			//게시글 비밀번호 입력
			System.out.print("게시글 비밀번호 입력 : ");
			String pw = scan.next();
			scan.nextLine();
			//입력한 비밀번호와 게시글의 비밀번호가 같으면
			if(pw.equals(posts.get(num-1).getPw())) {
				//수정할 제목과 내용 입력
				System.out.println("수정할 제목 입력 : ");
				String title = scan.nextLine();
				System.out.println("수정할 내용 입력 : ");
				String content = scan.nextLine();
				//게시글 수정
				if(posts.get(num-1).getId() == "Manager") {
					posts.get(num-1).modify("[공지] " + title, content);
				} else {
					posts.get(num-1).modify("[일반] " + title, content);
				}		
				printMessage("수정이 완료되었습니다");
			}else {
				printMessage("비밀번호가 틀렸습니다. 수정할 수 없습니다. 메뉴로 돌아갑니다.");
			}
			return;
		}catch (Exception e) {
			printMessage("잘못 입력했습니다. 메뉴로 돌아갑니다");
			return;
		}
	}//modifyPost
	
	//기능10) 게시물 삭제
	private void deletePost() {
		//게시판 목록
		printPostList();
		try {
			//삭제할 게시글 선택
			System.out.print("삭제할 게시글 선택 : ");
			int num = scan.nextInt();
			//예외 처리: num이 0보다 작거나 같거나, num이 posts.size보다 클 때
			if(num <= 0 || num > posts.size()) {
				throw new Exception();
			}
			//비밀번호 입력	
			System.out.print("게시글 비밀번호 입력 : ");
			String pw = scan.next();
			//입력한 비밀번호와 게시글의 비밀번호가 같으면
			if(pw.equals(posts.get(num-1).getPw())) {
				//게시물 삭제
				posts.remove(num-1);
				//번호 수정하고
				for(int i = num-1; i < posts.size(); i++) {
					posts.get(i).reduceNum();
				}
				//게시물수도 1감소
				Post.reduceCount();
				printMessage("삭제가 완료되었습니다");
			}else {
				printMessage("비밀번호가 틀렸습니다. 삭제할 수 없습니다. 메뉴로 돌아갑니다.");
			}
		} catch (Exception e) {
			printMessage("잘못 입력했습니다. 메뉴로 돌아갑니다");
			return;
		}
		
	}//deletePost
}
