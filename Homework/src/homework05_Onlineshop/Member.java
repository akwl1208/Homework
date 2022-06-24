package homework05_Onlineshop;

import java.util.*;

public class Member {
	//필드
	private String id, pw, name, phoneNum; //회원 아이디와 비밀번호, 이름과 전화번호
	private String clazz;//등급(관리자: ADMIN, 회원: MEMBER)
	private List<Product> basket = new ArrayList<Product>(); //장바구니
	private List<Order> order = new ArrayList<Order>(); //주문내역
	
	//생성자
	public Member(String id, String pw, String name, String phoneNum, String clazz) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phoneNum = phoneNum;
		setClazz(clazz);
	}//
	
	//생성자
	public Member() {} //
	
	//생성자
	public Member(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}//
	
	//생성자
	public Member(String id, String pw, String name, String phoneNum, String clazz, List<Product> basket) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phoneNum = phoneNum;
		setClazz(clazz);
		this.basket = basket;
	}//
	
	//getter, setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		if(!(clazz.equals("ADMIN") || clazz.equals("MEMBER")))
			throw new RuntimeException("없는 등급입니다"); 
		this.clazz = clazz;
	}

	public List<Product> getBasket() {
		return basket;
	}

	public void setBasket(List<Product> basket) {
		this.basket = basket;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}//

	@Override
	public String toString() {
		return id + "|" + pw + "|" + name + "|" + phoneNum + "|" + clazz;
	}
}//class member

class Order{
	Date orderDate; //주문일
	String orderCode; //주문번호
	String receiver, contactNum, deliveryAddr; //수령인, 연락처, 배송지
	List<Product> orderProduct = new ArrayList<Product>(); //주문제품
	int totalPrice; //총주문금액
	
	public Order(String receiver, String contactNum, String deliveryAddr, List<Product> orderProduct, int totalPrice) {
		this.orderCode = orderCode;
		this.orderDate = new Date();
		this.receiver = receiver;
		this.contactNum = contactNum;
		this.deliveryAddr = deliveryAddr;
		this.orderProduct = orderProduct;
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		;
		
		this.orderCode = orderCode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

	public List<Product> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(List<Product> orderProduct) {
		this.orderProduct = orderProduct;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
}
