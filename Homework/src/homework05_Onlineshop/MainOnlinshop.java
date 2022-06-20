package homework05_Onlineshop;

import java.util.Scanner;

public class MainOnlinshop {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		OnlineshopManager osm = new OnlineshopManager(scan);
		osm.run();
		
		scan.close();
	}

}
