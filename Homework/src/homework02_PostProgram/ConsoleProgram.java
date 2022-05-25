package homework02_PostProgram;

import java.util.Scanner;

public interface ConsoleProgram {
	//기능1) 메뉴 출력 후, 선택하는 기능
	int selectMenu(Scanner scan);
	
	//기능2) 선택한 메뉴에 맞게 기능 수행
	void excute(int menu);
	
	//기능3) 프로그램 실행
	void run();
}
