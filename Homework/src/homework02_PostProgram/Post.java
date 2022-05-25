package homework02_PostProgram;

import java.text.*;
import java.util.*;

public class Post implements Cloneable {
	//필드
	private String title; //게시글 제목
	private Date date; //작성 날짜
	private String id; //익명게시글이라서 아이디로
	private String pw; //게시글 수정, 삭제할 때 비밀번호
	private String content; //게시글 내용
	private int view; //조회수
	private int num; //게시글 번호
	
	
	private static int count = 0;
	
	//생성자1 -> 일반글용
	public Post(String title, String id, String pw, String content) {
		this.num = ++count;
		this.title = title;
		this.date = new Date();
		this.id = id;
		this.pw = pw;
		this.content = content;
		
	}//
	
	//생성자2 -> 공지글용
	public Post(String title, String content) {
		this.num = ++count;
		this.title = title;
		this.date = new Date();
		this.id = "Manager";
		this.pw = "manager";
		this.content = content;		
	}//
		
	//기본생성자
	public Post() {}//

	//기능1) 수정
	public void modify(String title, String content) {
		this.title = title;
		this.content = content;
	}//modify
	
	//기능2) 상세 출력
	public void printDetail() {
		System.out.println("=====================");
		System.out.println("번호 : " + num);
		System.out.println("제목 : " + title);
		System.out.println("작성자 : " + id);
		System.out.println("작성일 : " + getDate());
		System.out.println("조회수 : " + view);
		System.out.println("내용 : " + content);
		System.out.println("=====================");		
	}//printDetail
	
	//기능3) 조회수 증가
	public void updateView(){
		view++;
	}//updateView
	
	//기능4) 번호 감소
	public void reduceNum(){
		num--;
	}//reduceNum
	
	public static void reduceCount() {
		count--;
	}
	//toString
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("000");
		return df.format(num)+ " | " + title + " | " + id 
				+ " | "+ getDate() + " | " + view +" views";
	}//
	
	//getter and setter
	public String getDate() {
		if(date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);	
	}
	public String getPw() {
		return pw;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Post.count = count;
	}

	public String getId() {
		return id;
	}
	
	//
	
	
	
	
}
