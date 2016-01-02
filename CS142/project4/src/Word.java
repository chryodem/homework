

public class Word {
	
	private String vocabWord;
	
	private String definition;
	
	public Word (String vw, String d) {
		
		vocabWord = vw;
		
		definition = d;
		
				
	}
		
	public String getVocabWord(){
		return vocabWord;
	}
	public String getDefinition(){
		return definition;
	}
	public void setVocabWord(String vw){
		
		vocabWord = vw;
	}
	
	public void setDefinition(String d){
		
		definition = d;
		
	}

}
//public Class FlashCard
//private Word[]theWords = new Word [3];
// theWords[0]="whatever";

//public String toString();
// {return name;}
//
