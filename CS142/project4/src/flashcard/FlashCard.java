package flashcard;

public class Flashcard {
	private Word[] theWords;
	
	public static void main(String[] args) {
		Flashcard vocab = new Flashcard();
		vocab.add(new Word("Po","a star in Kungfu Panda"));
		System.out.println(vocab.theWords[0]);
		
		
	}

	public void add(Word word) {
		if (theWords == null){
			theWords = new Word[1];
	}
		
		else{
			Word[] temp = theWords;
			theWords = new Word[temp.length+1];
			for(int i = 0; i < temp.length; i++){
				theWords[i] = temp[i];
				
			}
		}
			theWords[theWords.length-1] = word;
			
		}
		public void printWords(){
			for (int i=0; i<theWords.length;i++){
				System.out.println(theWords[i]);
		}
	}
}
