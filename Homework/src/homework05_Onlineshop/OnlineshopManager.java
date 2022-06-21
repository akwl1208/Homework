package homework05_Onlineshop;

import java.util.*;
import java.util.regex.Pattern;

import homework02_PostProgram.ConsoleProgram;
import homework03_Student.Student;

public class OnlineshopManager implements ConsoleProgram {
	Scanner scan = new Scanner(System.in);
	private int exitmenu = 4;
	int adminMenu, memberMenu;
	List<Member> members = new ArrayList<Member>(); //회원들 정보
	List<Product> products = new ArrayList<Product>(); //제품목록
	char answer;
	
	public OnlineshopManager(Scanner scan) {
		this.scan = scan;
		inputTestData();
	}

	@Override
	public int selectMenu(Scanner scan) {
		System.out.println("*******의류 쇼핑몰*******");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 아이디/비밀번호 찾기");
		System.out.println("4. 프로그램 종료");
		System.out.println("***********************");
		System.out.print("메뉴 선택 : ");
		return scan.nextInt();	
	}

	@Override
	public void excute(int menu) {
		switch(menu) {
		case 1: //회원가입
			System.out.println("********회원가입********");
			//이용약관과 개인정보 수집 및 이용에 동의하면 회원가입 절차 진행
			System.out.println("이용약관과 개인정보 수집 및 이용에 동의하십니까?");
			System.out.print("동의 후 가입하기[네(y)/아니오(n)] : ");
			answer = scan.next().charAt(0);
			switch(answer) {
			case 'Y', 'y':
				//이름과 핸드폰번호 입력
				System.out.print("이름 : ");
				String name = scan.next();
				String phoneNum;
				while(true) { //형식에 맞지 않게 입력한 경우 다시 입력하기
					System.out.print("핸드폰 번호[예) 010-1234-5678] : ");
					phoneNum = scan.next();
					if(!Pattern.matches("(010)-\\d{4}-\\d{4}", phoneNum)) {
						printMessage("010-xxxx-xxxx 로 입력해주세요");
						continue;
					}
					break;
				}//while 전화번호 입력				
				//회원정보 중 핸드폰 번호가 같은 회원이 있으면 이미 회원가입되어 있다고 알려주기
				int count = 0;
				for(Member tmp : members) {
					if(phoneNum.equals(tmp.getPhoneNum()))
						count++;
				}
				if(count == 1){ //count가 1이면 이미 등록된 회원
					printMessage("이미 회원가입되어 있습니다. 메뉴에서 아이디/비밀번호 찾기를 해주세요");
					break;
				}	
				//일치하는 번호가 없으면 휴대폰 번호로 4자리 숫자(0000~9999)본인인증 메세지 전송됨
				//번호 확인이 틀리면 새로운 인증번호가 감 -> 총 3번 -> 3번 모두 틀리면 메뉴로 돌아감 
				int i;
				for(i = 1; i <= 3; i++) {
					String verification = "";
					for(int j = 0; j < 4; j++) { //4자리 수 생성
						int r = (int)(Math.random()*10);//0~9사이의 랜덤수 생성
						verification += Integer.toString(r);
					}
					printMessage("본인인증번호 : " + verification); //인증번호가 문자로 도착
					System.out.print("본인인증번호 입력 : ");
					String veriCheck = scan.next();
					//문자로 받은 본인인증번호와 입력한 번호가 일치하면 본인인증 성공
					if(veriCheck.equals(verification)) {
						printMessage("본인인증에 성공했습니다");
						break;
					}
					printMessage("본인인증번호와 일치하지 않습니다.");
				}				
				//본인인증 3번 실패 시 메뉴로 돌아감
				if(i == 4) { //i가 4면 본인인증 실패
					printMessage("본인인증에 실패했습니다. 메뉴로 돌아갑니다");
					break;
				}		
				//아이디 입력하면 중복확인 후 중복되지 않고 아이디가 3글자 이상 12글자 이하이면 비밀번호 입력
				printMessage("3이상 12이하 글자수로 영문과 숫자를 조합하여 아이디를 만들어주세요");
				String id;
				while(true) {
					System.out.print("아이디 : ");
					id = scan.next();
					//아이디 중복 확인
					count = 0;
					for(Member tmp : members) {
						if(id.equals(tmp.getId()))
							count++;
					}
					if(count == 1){ //count가 1이면 있는 아이디
						printMessage("중복된 아이디입니다. 다른 아이디를 만들어주세요");
						continue;
					}
					//아이디 글자수가 3글자 이상 12글자 이하인지 확인 -> 아니면 다시 아이디 입력
					if(id.length() < 3 || id.length() > 12) {
						printMessage("3글자 이상 12글자 이하로 아이디를 만들어주세요");
						continue;
					}
					//영문과 숫자로만 썼는지
					if(!Pattern.matches("\\w+", id)) {
						printMessage("알파벳(a-zA-Z)과 숫자(0-9)만 사용해주세요");
					}
					printMessage("사용가능한 아이디입니다"); //중복도 아니고 아이디 글자수 조건도 통과
					break;
				}//while 아이디 
				//비밀번호와 비밀번호 확인을 입력하고 일치하면 회원가입 성공
				String pw;
				while(true) {
					System.out.print("비밀번호 : ");
					pw = scan.next();
					System.out.print("비밀번호 확인 : ");
					String pwCheck = scan.next();
					//비밀번호 중복 확인
					if(!pwCheck.equals(pw)) {
						printMessage("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
						continue;
					}	
					break;
				}//while 비밀번호 질문
				members.add(new Member(name, phoneNum, id, pw, "MEMBER"));
				printMessage("회원가입이 완료되었습니다");
				
				break;
			case 'N', 'n':
				printMessage("비동의시 회원가입할 수 없습니다. 메뉴로 돌아갑니다");
				break;
			default : printMessage("y 또는 n을 입력하세요");
			}	
			break;		
		case 2: //로그인
			System.out.println("********로그인********");
			String id,pw;
			//아이디 비밀번호 입력 -> 3회 다르게 입력하면 메뉴로 돌아감
			int i, j = 0, count = 0, joinCheck = 0;
			for(i = 1; i <= 3; i++) {
				System.out.print("아이디 : ");
				id = scan.next();
				System.out.print("비밀번호 : ");
				pw = scan.next();
				for(j = 0; j < members.size();j++) {
					if(id.equals(members.get(j).getId())) { //아이디가 있는지 확인
						joinCheck++;
					}
					if(pw.equals(members.get(j).getPw())){ //패스워드 확인
						count++;
						break;
					}
				}
				if(joinCheck == 0) { //joincheck가 0이면 등록되지 않은 아이디
					printMessage("등록되지 않은 아이디입니다. 회원가입해주세요");
					break;
				}
				if(count == 1) { //count가 1이면 로그인 성공
					printMessage("로그인에 성공했습니다");
					break;
				} //둘다 1이 아니면면 로그인 정보가 일치하지 않음
				printMessage("로그인 정보가 일치하지 않습니다");
			}				
			//등록되지 않은 아이디인 경우 메뉴로 돌아감
			if(joinCheck == 0) {
				break;
			}
			//로그인 3번 실패 시 메뉴로 돌아감 
			if(i == 4) { //i가 4이면 로그인 실패함
				printMessage("로그인에 실패했습니다. 아이디/비밀번호 찾기 해주세요 ");
				break;
			}
			
			//관리자면 관리자 메뉴 출력 회원이면 회원 메뉴 출력
			Member m = members.get(j); 
			switch(m.getClazz()) {
			case "ADMIN" : //관리자
				do {
					System.out.println("*******관리자 메뉴*******");
					System.out.println("1. 제품 등록");
					System.out.println("2. 제품 수정");
					System.out.println("3. 제품 삭제");
					System.out.println("4. 제품 확인");
					System.out.println("5. 주문 확인");
					System.out.println("6. 로그아웃");
					System.out.println("7. 프로그램 종료");
					System.out.println("***********************");
					System.out.print("메뉴 선택 : ");
					adminMenu = scan.nextInt();
					
					switch(adminMenu) {
					case 1: //제품 등록
						printMessage("제품 등록합니다");
						while(true) { //제품 추가는 원할 때까지 반복
							System.out.print("제품코드 : ");
							String code = scan.next();
							//outer는 O, top은 T, bottom은 B, dress는 D
							if(code.charAt(0) != 'O' && code.charAt(0) != 'T' && code.charAt(0) != 'B' && code.charAt(0) != 'D') {
								printMessage("등록할 수 없는 제품입니다. 확인해주세요");
								break;
							}
							//제품코드가 있는지 확인 
							count = 0;
							for(Product tmp : products) {
								if(code.equals(tmp.getCode()))
									count++;
							}
							if(count == 1){ //count가 1이면 이미 등록된 제품
								printMessage("이미 등록된 제품입니다");
								break;
							}
							//카테고리 저장
							String category = "";
							switch(code.charAt(0)) {
							case 'O' : category = "outer"; break;
							case 'T' : category = "top"; break;
							case 'B' : category = "bottom"; break;
							case 'D' : category = "dress"; break;
							default: 
							}
							//제품명 입력
							System.out.print("제품명 : ");
							String name = scan.next();
							//옵션은 입력 후 추가할지 묻기 
							List<Option> option = new ArrayList<Option>();				
							while(true) { //옵션에서 색상과 사이즈 중복 체크 추가
								System.out.print("색상 : ");
								String color = scan.next();
								System.out.print("사이즈 : ");
								String size = scan.next();
								System.out.print("수량 : ");
								int amount = scan.nextInt();
								option.add(new Option(color, size, amount));
								while(true) {
									System.out.print("옵션을 더 추가하시겠습니까?[y/n] : ");		
									answer = scan.next().charAt(0); 
									if(answer == 'y' || answer == 'n')
										break;
									else //y나 n 이외의 값을 입력하면 옵션 추가할지 다시 묻기
										printMessage("y나 n을 입력하세요");
								}
								if(answer == 'n') //n이면 반복문 나와 가격 묻기
									break;
							}//while 옵션 추가 
							//n을 입력하면 가격 입력
							System.out.print("가격 : ");
							int price = scan.nextInt();
							products.add(new Product(code, category, name, option, price));
							printMessage(code + "|" + name + " 이(가) 등록됬습니다");
							while(true) {
								System.out.print("제품을 더 추가하시겠습니까?[y/n] : ");		
								answer = scan.next().charAt(0); 
								if(answer == 'y' || answer == 'n')
									break;
								else //y나 n 이외의 값을 입력하면 제품 추가할지 다시 묻기
									printMessage("y나 n을 입력하세요");
							}//while 제품 추가 더 할지 질문
							if(answer == 'n') //n이면 반복문 나와 관리자 메뉴 출력
								break;
						}//while 제품 추가
						break;
					case 2: //제품 수정
						printMessage("제품 수정합니다");
						while(true) { //제품 수정은 원할 때까지 반복
							//제품코드를 기준으로 정렬
							products.sort(new Comparator<Product>(){
								@Override
								public int compare(Product o1, Product o2) {
									return o1.getCode().compareTo(o2.getCode());
								}
							});
							System.out.println("***********************");
							for(int k = 0; k < products.size(); k++) //가지고 있는 제품들을 보여줌
								System.out.println(k+1 + ". " + products.get(k).getCode() + " | " + products.get(k).getName());
							System.out.println("***********************");
							System.out.print("수정할 제품 번호를 입력하세요 : ");
							int prodNum = scan.nextInt();
							
							Product p = products.get(prodNum-1); //제품 임시 저장
							printMessage(p.getCode() + "을 수정합니다");
							char answer;
							while(true) { //제품명 수정
								System.out.print("제품명을 수정하시겠습니까?[y/n] : ");
								answer = scan.next().charAt(0);
								if(answer =='y') {
									System.out.print("제품명 : ");
									p.setName(scan.next());
								}else if(answer != 'n') {
									printMessage("y나 n을 입력하세요");
									continue;
								}
								break;
							}//while 제품 수정할지 질문
							while(true) { //옵션 수정
								System.out.print("옵션을 수정하시겠습니까?[y/n] : ");	
								answer = scan.next().charAt(0);
								if(answer =='y') { //제품의 옵션들을 보여줌
									System.out.println("***********************");
									for(int o = 0; o < p.getOption().size(); o++) 
										System.out.println(o+1 + ". " + p.getOption().get(o));
									System.out.println("***********************");
									while(true) {
										System.out.print("수정할 옵션 번호를 입력하세요: ");
										int optNum = scan.nextInt();
										System.out.print("색상 : ");
										String color = scan.next();
										System.out.print("사이즈 : ");
										String size = scan.next();
										System.out.print("수량 : ");
										int amount = scan.nextInt();
										p.getOption().get(optNum-1).modifyOption(color, size, amount);
										while(true) {
											System.out.print("옵션을 더 수정하시겠습니까?[y/n] : ");		
											answer = scan.next().charAt(0); 
											if(answer == 'y' || answer == 'n')
												break;
											else //y나 n 이외의 값을 입력하면 옵션 수정할지 다시 묻기
												printMessage("y나 n을 입력하세요");
										}
										if(answer == 'n') //n이면 반복문 나와 가격 묻기
											break;
									}//while 제품 옵션 번호 질문
								}else if(answer != 'n') { //y 또는 n 이외의 값 입력한 경우 옵션 수정할지 묻기
									printMessage("y나 n을 입력하세요");
									continue;
								}
								break;
							}//while 제품 옵션
							while(true) { //가격 수정
								System.out.print("가격을 수정하시겠습니까?[y/n] : ");
								answer = scan.next().charAt(0);
								if(answer =='y') {
									System.out.print("가격 : ");
									p.setPrice(scan.nextInt());
								}else if(answer != 'n') {
									printMessage("y나 n을 입력하세요");
									continue;
								}
								break;
							}//while 가격 수정 질문
							printMessage(p.getCode() + "을 수정했습니다");
							while(true) {
								System.out.print("제품을 더 수정하시겠습니까?[y/n] : ");		
								answer = scan.next().charAt(0); 
								if(answer == 'y' || answer == 'n')
									break;
								else //y나 n 이외의 값을 입력하면 제품 수정할지 다시 묻기
									printMessage("y나 n을 입력하세요");
							}//while 추가 수정 질문
							if(answer == 'n') //n이면 반복문 나와 관리자 메뉴 출력
								break;	
						}//while 제품 수정
						break;
					case 3: //제품 삭제
						printMessage("제품 삭제합니다");
						while(true) { //제품 삭제는 원할 때까지 반복
							//제품코드를 기준으로 정렬
							products.sort(new Comparator<Product>(){
								@Override
								public int compare(Product o1, Product o2) {
									return o1.getCode().compareTo(o2.getCode());
								}
							});
							System.out.println("***********************");
							for(int k = 0; k < products.size(); k++) //가지고 있는 제품들을 보여줌
								System.out.println(k+1 + ". " + products.get(k).getCode() + " | " + products.get(k).getName());
							System.out.println("***********************");
							System.out.print("삭제할 제품 번호를 입력하세요 : ");
							int prodNum = scan.nextInt();
							Product p = products.get(prodNum-1); //제품 임시 저장
							while(true) {
								System.out.print(p.getCode() + " | " +  p.getName() + " 을(를) 삭제하시겠습니까?[y/n] : ");
								char answer = scan.next().charAt(0);
								if(answer =='y') {
									products.remove(p);
									printMessage(p.getCode() + "을 삭제했습니다");
									while(true) {
										System.out.print("제품을 더 삭제하시겠습니까?[y/n] : ");		
										answer = scan.next().charAt(0); 
										if(answer == 'y' || answer == 'n')
											break;
										else //y나 n 이외의 값을 입력하면 제품 삭제할지 다시 묻기
											printMessage("y나 n을 입력하세요");
									}//while 제품 추가 삭제 질문
								}else if(answer != 'n') {
									printMessage("y나 n을 입력하세요");
									continue;
								}
								break;
							}//while 삭제할 제품 삭제
							if(answer == 'n') //n이면 반복문 나와 관리자 메뉴 출력
								break;
						}//while 제품 목록
						break;
					case 4: //제품 확인
						//제품코드를 기준으로 정렬
						products.sort(new Comparator<Product>(){
							@Override
							public int compare(Product o1, Product o2) {
								return o1.getCode().compareTo(o2.getCode());
							}
						});	
						for(Product tmp : products) {
							System.out.println(tmp);
						}
						break;
					case 5: //주문 내역 확인
						break;
					case 6: printMessage("로그아웃됩니다"); break;
					case 7: break;
					default: 
					}
				}while(adminMenu != 6 && adminMenu != 7);
				break;
				
			case "MEMBER" : //회원
				do {
					System.out.println("*******회원 메뉴*******");
					System.out.println("1. 제품 보기");
					System.out.println("2. 장바구니 확인");
					System.out.println("3. 주문 내역 확인");
					System.out.println("4. 로그아웃");
					System.out.println("5. 프로그램 종료");
					System.out.println("***********************");
					System.out.print("메뉴 선택 : ");
					memberMenu = scan.nextInt();
					
					switch(memberMenu) {
					case 1: //제품 보기
						products.sort(new Comparator<Product>(){
							@Override
							public int compare(Product o1, Product o2) {
								return o1.getCode().compareTo(o2.getCode());
							}
						});
						while(true) { //장바구니 담아도 계속 쇼핑한다고 하면 다시 제품 목록 보여주기 위해 반복문
							System.out.println("***********************");
							System.out.println("제품 목록");
							for(int k = 0; k < products.size(); k++) {
								Product p = products.get(k);
								System.out.println(k+1 + ". " + p.getCategory() + " | " + p.getName() + " | " + p.getPrice());
							}
							System.out.println("***********************");
							System.out.print("제품 번호를 선택하세요[메뉴로 돌아가기 : -1] : ");
							int prodNum = scan.nextInt();
							if(prodNum == -1) { //-1 누르면 메뉴로 돌아가기
								break;
							}	
							Product p = products.get(prodNum-1); //제품 임시 저장
							//선택한 제품의 옵션을 보여준다.
							System.out.println("***********************");
							System.out.println("제품명 : " + p.getName());
							for(int k = 0; k < p.getOption().size(); k++) {
								System.out.print("옵션 " + (k+1) + " : "); //수량이 0인 옵션은 품절이라고 보여준다
								List<Option> o = p.getOption(); //제품의 옵션들 임시 저장
								System.out.println(o.get(k).getAmount() > 0?o.get(k):o.get(k).getColor() + "|" + o.get(k).getSize() + "|품절");
							}
							System.out.println("가격 : " + p.getPrice());
							System.out.println("***********************");
							count = 0;
							char ok;
							while(true) { //장바구니 담기
								System.out.print("장바구니에 담으시겠습니까?[y/n] : ");
								ok = scan.next().charAt(0);
								if(ok == 'n') //장바구니에 안담겠다고(n) 하면 제품 목록 보여준다.
									break;
								if(ok =='y') {
									int optionNum;
									while(true) { //없는 옵션을 선택하거나 품절인 상품을 선택하면 다시 옵션 번호를 입력하도록 함
										System.out.print("옵션 번호: ");
										optionNum = scan.nextInt();
										if(optionNum > p.getOption().size()) {
											printMessage("없는 옵션입니다. 다시 선택해주세요");
											continue;
										}
										if(p.getOption().get(optionNum-1).getAmount() <= 0) {
											printMessage("품절인 상품입니다. 다시 선택해주세요");
											continue;
										}
										break;
									}//while 옵션번호
									int orderAmount;
									Option option;
									while(true) { //가지고 있는 수량을 초과해서 구매수량을 입력하면 구매 수량을 다시 입력하도록 함
										System.out.print("구매 수량 : ");
										orderAmount = scan.nextInt();
										if(orderAmount > p.getOption().get(optionNum-1).getAmount()) {
											printMessage("수량을 초과했습니다. 다시 입력해주세요");
											continue;
										}								
										//제품 옵션 수량 수정 -> 예를 들면 10개 중 2개를 장바구니에 담았으면 8로 수정
										option= p.getOption().get(optionNum-1);
										option.setAmount(option.getAmount()-orderAmount);
										break;
									}//while 구매수량
									//이미 장바구니에 담겨있는지 확인
									int k; int z = 0; count = 0; //k : 장바구니 index, z : 옵션 인덱스
									for(k = 0; k < m.getBasket().size(); k++) {
										List<Product> b = new ArrayList<Product>(m.getBasket());
										if(b.get(k).getCode().equals(p.getCode())) { //제품코드 확인
											List<Option> op = new ArrayList<Option>(b.get(k).getOption());
											for(z = 0; z < op.size(); z++) { //옵션의 색상과 크기가 같은 경우
												if(op.get(z).getColor().equals(option.getColor()) && op.get(z).getSize().equals(option.getSize())) {
													printMessage("이미 장바구니에 담긴 제품입니다");
													count++;
													break;
												}//if 옵션의 색상 사이즈 비교
											}//for 옵션
										}//if 제품코드 비교
										if(count == 1) {
											break;
										}
									}//for 장바구니
									
									//장바구니에 이미 담긴 제품인 경우 count가 1
									if(count == 1) {
										while(true){
											System.out.print("장바구니에 추가하겠습니까?[y/n] : ");
											answer = scan.next().charAt(0);
											if(answer == 'y') { //장바구니에 담는다고 한 경우
												//장바구니 수정		
												Product basket = m.getBasket().get(k);
												Option basOpt = basket.getOption().get(z);
												//옵션 수량 수정 = 기존 수량 + 구매 수량
												basOpt.setAmount(basOpt.getAmount() + orderAmount);
												//가격 수정
												basket.setPrice(basket.getPrice() + orderAmount*p.getPrice());
												printMessage(basket.getName() + "이(가) 장바구니에 담겼습니다");
											}else if(answer != 'n') {
												printMessage("y나 n을 입력하세요");
												continue;
											}
											break;
										}//while 장바구니 추가할지 
									}//if count == 1
									//처음 장바구니에 담는 제품인 경우 count는 0
									if(count == 0) {
										//선택한 제품 임시 객체에 저장				
										Product selectProduct = new Product(p);
										//옵션 수정
										Option opt = new Option(selectProduct.getOption().get(optionNum-1));
										opt.setAmount(orderAmount); //구매한 수량으로 수정
										List<Option> o = new ArrayList<Option>();
										o.add(opt); //구매한 옵션만 넣기
										selectProduct.setOption(o);
										//가격 수정
										selectProduct.setPrice(selectProduct.getPrice()*orderAmount);
										m.getBasket().add(selectProduct); //장바구니에 담기
										printMessage(selectProduct.getName() + "이(가) 장바구니에 담겼습니다");
									}//if count == 0
									while(true) {
										System.out.print("계속 쇼핑하시겠습니까?[y/n] : ");
										answer = scan.next().charAt(0);
										if(answer == 'y' || answer == 'n')
											break;
										else //y나 n 이외의 값을 입력하면 제품 수정할지 다시 묻기
											printMessage("y나 n을 입력하세요");
									}//while 쇼핑 계속할지
								}else if(ok != 'n'){
									printMessage("y나 n을 입력하세요");
									continue;	
								}
								break;
							}//while 장바구니 담기
							if(ok == 'n' || answer == 'y') //ok가 n이거나 answer이 y면 제품 목록을 보여줘야함
								continue;
							break;
						}//while 제품 목록
						break;
					case 2: //장바구니 보기
						List<Product> basket = m.getBasket(); 
						int totalPrice = 0;
						for(int k = 0; k < basket.size(); k++) {
							Product b = basket.get(k);
							System.out.println(k+1 + ". " + b.getName() + " | " + b.getOption() + " | " + b.getPrice());
							totalPrice += b.getPrice();
						}
						printMessage("총 결제 금액 : " + totalPrice);
						
						System.out.println("*******장바구니 메뉴*******");
						System.out.println("1. 제품 구매");
						System.out.println("2. 장바구니 비우기");
						System.out.println("***********************");
						System.out.print("메뉴 선택 : ");
						int basketMenu = scan.nextInt();
						switch(basketMenu){
						case 1 : //제품 구매 
							break;
						case 2 : //장바구니 비우기
							basket.clear();
							printMessage("장바구니가 비웠습니다");
							break;
						default : printMessage("잘못된 메뉴를 선택했습니다");
						}
						
						break;
					case 3: //구매내역 보기
				
						break;
					case 4: //로그아웃
						printMessage("로그아웃됩니다"); break;
					case 5: break;
					default: 
					}
				}while(memberMenu != 4 && memberMenu != 5);
				break;
			default : printMessage("관리자에게 문의해주세요. 짱아: 010-1234-5679");
			}
			
			break;		
		case 3: 
			for(Member tmp : members) {
				System.out.println(tmp);
			}
			
			break;		
		default:	
		}
		
	}

	@Override
	public void run() {
		int menu = -1;
		do {	
			try {
				menu = selectMenu(scan);
				excute(menu);
				if(adminMenu == 7 || memberMenu == 5) //관리자 메뉴가 7이면 프로그램종료/회원메뉴가 5이면 프로그램 종료
					menu = 4;
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}while(menu != exitmenu);
		printMessage("방문해주셔서 감사합니다. 다음에 또 오세요");
	}

	//기능1)
	private void printMessage(String str) {
		System.out.println("***********************");
		System.out.println(str);
		System.out.println("***********************");
	}//printMessage
	
	//기능2)
	private void inputTestData() {
		//제품 데이터
		List<Option> opt1 = new ArrayList<Option>();
		opt1.add(new Option("검정", "90", 10));
		opt1.add(new Option("흰색", "90", 0));
		List<Option> opt2 = new ArrayList<Option>();
		opt2.add(new Option("검정", "90", 10));
		products.add(new Product("T001", "TOP", "반팔", opt1, 5000));
		products.add(new Product("T002", "TOP", "긴팔", opt2, 7000));
		//장바구니 데이터
		List<Product> basket = new ArrayList<Product>();
		List<Option> basketOpt1 = new ArrayList<Option>();
		basketOpt1.add(new Option("검정", "90", 2));
		basket.add(new Product("T001", "TOP", "반팔", basketOpt1, 10000));
		List<Option> basketOpt2 = new ArrayList<Option>();
		basketOpt2.add(new Option("검정", "90", 2));
		basket.add(new Product("T002", "TOP", "긴팔", basketOpt2, 14000));
		//회원 데이터
		members.add(new Member("abc123", "abc123", "짱구", "010-1234-5678", "MEMBER", basket));
		members.add(new Member("admin", "1234", "짱아", "010-1234-5679", "ADMIN"));
	
	}//inputTestData
}
