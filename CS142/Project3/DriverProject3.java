package flashcard;

public class DriverProject3 {
	public static void main(String[] args) {
		int points = 0;
		int totalPoints = 0;
		int pointsPerReq = 5;
		String word1Definition = "a painful way of turning time and money into a piece of paper";
		String word1VocabWord = "class";
		try{
			//Test the constructor for Word
			Word word1 = new Word(word1VocabWord, word1Definition);
		
			//Test the word definition getter
			totalPoints += pointsPerReq;
			if(word1.getDefinition().equals(word1Definition)){
				System.out.println("Word definition getter works");
				points += pointsPerReq;
			}
			else{
				System.out.println("Word definition getter doesn't work");
			}
			
			//Test the vocabWord getter
			totalPoints += pointsPerReq;
			if(word1.getVocabWord().equals(word1VocabWord)){
				System.out.println("Word vocabWord getter works");
				points += pointsPerReq;
			}
			else{
				System.out.println("Word vocabWord getter doesn't work");
			}
			
			//Test the setDefinition method
			word1Definition = "writing project drivers for cs142";
			word1.setDefinition(word1Definition);
			totalPoints += pointsPerReq;
			if(word1.getDefinition().equals(word1Definition)){
				System.out.println("Word definition setter works");
				points += pointsPerReq;
			}
			else{
				System.out.println("Word definition setter doesn't work");
			}
			
			//Test the setVocabWord method			
			word1VocabWord = "monotonous";
			word1.setVocabWord(word1VocabWord);
			totalPoints += pointsPerReq;
			if(word1.getVocabWord().equals(word1VocabWord)){
				System.out.println("Word vocabWord setter works");
				points += pointsPerReq;
			}
			else{
				System.out.println("Word vocabWord setter doesn't work");
			}
		}
		catch(Exception e){
			System.out.println("Please make sure you defined your constructor, getDefinition, getVocabWord, setDefinition, and setVocabWord. Something is missing. Exiting");
			System.exit(0);
		}
		System.out.println("Your points for this assignment:" + points + "/" + totalPoints);
	}
}

