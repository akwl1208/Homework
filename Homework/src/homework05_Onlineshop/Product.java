package homework05_Onlineshop;

import java.util.*;

public class Product {
	//필드
	private String code, category, name; //제품코드, 카테고리, 제품명
	private List<Option> option = new ArrayList<Option>(); //제품의 옵션
	private int price; //가격
	
	//생성자
	public Product(String code, String category, String name, List<Option> option, int price) {
		this.code = code;
		this.category = category;
		this.name = name;
		this.option = option;
		setPrice(price);
	}//
	//생성자
	public Product(Product p) {
		this(p.getCode(), p.getCategory(), p.getName(), p.getOption(), p.getPrice());
	}//
	
	//메소드 : 옵션을 수정하는 
	public void modifyOption(Option o) {
		List<Option> tmp = new ArrayList<Option>();
		tmp.add(o);
		setOption(tmp);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Option> getOption() {
		return option;
	}

	public void setOption(List<Option> option) {
		this.option = option;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if(price < 0)
			throw new RuntimeException("가격은 0원 이상 입력하세요");
		this.price = price;
	}

	@Override
	public String toString() {
		return code + " | " + category + " | " + name + " | " + option + " | "+ price + "원";
	}
	
	

}//class product

class Option{
	//필드
	String color, size; //색상, 사이즈, 수량
	int amount;
	//생성자
	public Option(String color, String size, int amount) {
		this.color = color;
		this.size = size;
		setAmount(amount);
	}//
	public Option(Option option) {
		this(option.getColor(), option.getSize(), option.getAmount());
	}
	public void modifyOption(String color, String size, int amount) {
		this.color = color;
		this.size = size;
		setAmount(amount);
	}
	//getter,setter
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		if(amount < 0)
			throw new RuntimeException("0이상이여야함");
		this.amount = amount;
	}//
	@Override
	public String toString() {
		return color + " | " + size + " | " + amount + "개";
	}
	
}//class option
