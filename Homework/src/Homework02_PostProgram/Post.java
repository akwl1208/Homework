package Homework02_PostProgram;

public class Post implements Cloneable {
	//필드
	private String title; //게시글 제목
	private String date; //작성 날짜
	private String id; //익명게시글이라서 아이디로
	private String content; //게시글 내용
	private int view; //조회수
	
	//생성자1
	public Post(String title, String date, String id, String content) {
		this.title = title;
		this.date = date;
		this.id = id;
		this.content = content;
	}//
	
	//생성자2
	public Post(String title) {
		this.title = title;
	}//
	
	//기능1) 수정
	public void modify(String title, String date, String id, String content) {
		this.title = title;
		this.date = date;
		this.id = id;
		this.content = content;
	}//
	
	//기능2) 제목 출력
	public void printTitle() {
		System.out.println(title);
	}
		
	//toString
	@Override
	public String toString() {
		return  title + "\n 날짜 : " + date + ", 작성자 : " + id + ", 조회수 : " + view + "\n[내용]\n" + content + "";
	}//
	
	//getter and setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}//
	
	//clone
	public Post clone() {
		try {
			Post post = (Post)super.clone();
			//post.id = new String(id);
			return post;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}	
	}//

	//hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + view;
		return result;
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
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (view != other.view)
			return false;
		return true;
	}//
	
	
	
}
