package flashcard;

public class Exam1Option1 {

	String myString;

	int myGrade;

	public Exam1Option1(String string, int grade) {
		myString = string;

		myGrade = grade;

	}

	public String toString() 
	{
		return "my grade is " + myGrade;

	}

	public void setGrade(int i) {
		myGrade = i;

	}

	public int getGrade() {
		return myGrade;
	}

}
