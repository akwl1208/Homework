package homework02_PostProgram;

import java.util.Scanner;

public class MainPostProgram {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PostProgramManager ppm = new PostProgramManager(scan);
		ppm.run();
		scan.close();

	}

}
