package flashcard;

import java.io.IOException;

//Instructions: Implement the class and the necessary methods 
//(a constructor, a getter and a setter) so that this driver 
//prints out 100 on line 21. What line 21
//outputs is your grade for this exam.
//
//YOU ARE NOT ALLOWED TO CHANGE THE CODE IN THIS FILE
//
//HINTS:
// 1) The class you write should be called Exam1Option1
// 2) You need to create Exam1Option1.java and define one constructor and three methods 
//    there for this driver to work.
// 3) Pay attention to the code in the driver to see what those methods need to do.
//////////////////////////////////////////////////////////////
public class Exam1Option1Driver {
	public static void main(String[] args){
		int grade = 0;
		Exam1Option1 exam1Option1Instance = new Exam1Option1("my grade is", grade);
		if(exam1Option1Instance.toString().equals("my grade is " + grade)) {
			grade += 1;
		}
		exam1Option1Instance.setGrade(grade);
		int testGetGrade = exam1Option1Instance.getGrade(); 
		if(testGetGrade == 1) {
			grade++;
		}
		System.out.println(grade * 50);
	}
}

