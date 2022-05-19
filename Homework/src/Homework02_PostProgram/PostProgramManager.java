package Homework02_PostProgram;

import java.util.*;
//익명 게시글....
public class PostProgramManager implements ConsoleProgram {
	Scanner scan = new Scanner(System.in);
	private int exitmenu = 4;
	List<Post> posts = new ArrayList<Post>(); //게시판..
	List<Post> titles = new ArrayList<Post>(); //게시글 확인을 위해 제목만 따로 저장..
	private int managerpw = 1234;
	
	//생성자1
	public PostProgramManager(Scanner scan) {
		this.scan = scan;
		posts = new ArrayList<Post>();
		//테스트용
		Post post1 = new Post("[공지]게시글 작성 규칙", "2022-05-19", "Manager", "바른말 고운말 사용");
		Post title1 = new Post("[공지]게시글 작성 규칙");
		posts.add(post1);	
		titles.add(title1);
		Post post2 = new Post("[일반]안녕하세요", "2022-05-19", "아지짱짱", "아지는 세젤귀");
		Post title2 = new Post("[일반]안녕하세요");
		posts.add(post2);
		titles.add(title2);
	}

	@Override
	public int selectMenu(Scanner scan) {
		
		System.out.println("**익명게시판에 오신걸 환영합니다**");
		System.out.println("1. 게시글 등록(공지/일반)");
		System.out.println("2. 게시글 확인");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 프로그램 종료");
		System.out.println("*************************");
		System.out.print("메뉴 입력 : ");
		int menu = scan.nextInt();
		System.out.println("*************************");

		return menu;
	}

	@Override
	public void excute(int menu) {
		switch(menu) {
		case 1: //<게시글 등록(공지/일반)>
			//공지글 등록할건지 일반 게시글 올린건지 묻기
			System.out.println("게시글을 등록하시겠습니까?[공지(1)/일반(2)] : ");
			int submenu = scan.nextInt();
			switch(submenu) {
			case 1: //공지글 등록은 관리자만 가능하기 때문에 비밀번호를 입력해야함
				System.out.println("공지는 관리자만 등록 가능합니다");
				System.out.print("비밀번호를 입력하세요 : ");
				int pw = scan.nextInt();
				
				//입력한 비밀번호가 관리자 비밀번호가 같다면
				if(pw == managerpw) {
					//게시글 작성..
					System.out.println("게시글 제목을 입력하세요 : ");
					String title = scan.next();
					System.out.println("작성 날짜를 입력하세요 : ");
					String date = scan.next();
					System.out.println("내용을 입력하세요 : ");
					String content = scan.next();
					//작성한 내용을 게시글에 저장
					Post post = new Post("[공지]"+title, date, "Manager", content);
					//게시글을 Posts에 올리기...
					posts.add(post);
					printMessage("게시글이 등록됬습니다");
					scan.nextLine();
				}
				break;
			case 2: //일반 공지글은 아무나 올릴 수 있음
				//게시글 작성..
				System.out.println("게시글 제목을 입력하세요 : ");
				String title = scan.next();
				System.out.println("작성 날짜를 입력하세요 : ");
				String date = scan.next();
				System.out.println("아이디를 입력하세요 : ");
				String id = scan.next();
				System.out.println("내용을 입력하세요 : ");
				String content = scan.next();
				//작성한 내용을 게시글에 저장
				Post post = new Post("[일반]"+title, date, id, content);
				//게시글을 Posts에 올리기...
				posts.add(post);
				//등록됬다고 알려주기
				printMessage("게시글이 등록됬습니다");
				scan.nextLine();
				break;
			}
			break;
			
		case 2: //게시글 확인하기 ->제목만 보고 보고싶은 게시글 클릭 -> 게시글 확인 -> 조회수 증가
			//제목만 출력 -> 하는거 모르겠다... 
			System.out.println("게시글 출력");
			for(int i = 0; i < posts.size(); i++) {
				System.out.println(i+1 + " ");
			}
		
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			
			
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
				printMessage("잘못된 메뉴를 입력했습니다");
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
}
