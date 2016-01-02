//**********************************************************************
//	Word.java									Author:	Jesse Howell
//						Date: 2011 June 27		Last Edited: 2011 June 27
//	Exercise: 
//**********************************************************************

package flashcard;

public class Word {
	private String vocabWord;
	private String definition;
	
	//	constructor
	public Word(String vw, String d) {
		setVocabWord(vw);
		setDefinition(d);
	}
	
	public void setVocabWord (String vw) {
		this.vocabWord = vw;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public String getVocabWord() {
		return vocabWord;
	}
	public String getDefinition() {
		return definition;
	}
	
	public String toString() {
		return "\"" + getVocabWord() + "\": " + getDefinition();
	}
}
