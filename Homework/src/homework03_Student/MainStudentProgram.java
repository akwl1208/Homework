package homework03_Student;

import java.util.*;

public class MainStudentProgram {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		StudentProgramManager spm = new StudentProgramManager(scan);
		spm.run();
		
		scan.close();

	}

}
