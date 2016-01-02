//**********************************************************************
//	Word.java									Author:	Jesse Howell
//						Date: 2011 June 29		Last Edited: 2011 July 6
//	Exercise: 
//**********************************************************************
package flashcard;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Flashcard {
	private Word[] theWords;
	String fileName;
	
	//	constructor
	public Flashcard(Word[] theWords0, String fileName0) {
		this.theWords = theWords0;
		this.fileName = fileName0;
	}

	public String toString() {
		String print = "";
		for(Word t:theWords) {
			print += t.toString() + "\n";
		}
		return print;
	}
	
	public Word[] getTheWords() {
		return this.theWords;
	}
	
	public String[][] getDoubleArray() {
		String[][] flashcardArray = new String[theWords.length][2];
		int count = 0;
		while (count < theWords.length) {
			flashcardArray[count][0] = theWords[count].getVocabWord();
			flashcardArray[count][1] = theWords[count].getDefinition();
			
			count++;
		}
		return flashcardArray;
	}
	
	//	Why on earth is this supposed to be a boolean?!
	public boolean readData(String args) throws IOException, BadFormatException {
		Scanner fileScan;
		int wordNumber = 0;
				
		fileScan = new Scanner (new File(args));
		fileScan.useDelimiter("[<>\n\r]");
		// format is now <vocab><word>WORD</word><def>DEFINITION</def></vocab> ...sorta
		//				    0      1   2     3     4       5       6      7
		//						   7   8     9     10      11      12     13
		
		String nextWord = "";
		boolean oddVocab = false;
		boolean oddWord = false;
		boolean oddDef = false;
		boolean firstTime = true;
		
		while (fileScan.hasNext()) {
			nextWord = fileScan.next();
			
			if (firstTime) {
				if (!nextWord.equals("vocab")) {
					throw new BadFormatException("Error: File \"" + args + "\" is formatted incorrectly." +
					"\n\"<vocab>\" is missing.");
				} else {
					oddVocab = true;
					firstTime = false;
				}
			}
			
			if (nextWord.equals("word"))
				if(oddWord) {
				throw new BadFormatException("Error: File \"" + args + "\" is formatted incorrectly." +
						"\n\"</word>\" is missing.");
				} else {
					nextWord = fileScan.next();
					theWords[wordNumber] = new Word(nextWord, "");
					oddWord = true;
				}
			
			if (nextWord.equals("def")) {
				if(oddDef) {
					throw new BadFormatException("Error: File \"" + args + "\" is formatted incorrectly." +
					"\n\"</def>\" is missing.");
				} else {
					nextWord = fileScan.next();
					this.theWords[wordNumber].setDefinition(nextWord);
					oddDef = true;
					wordNumber++;
				}
			}

			if (nextWord.equals("/word")) {
				if(!oddWord) {
				throw new BadFormatException("Error: File \"" + args + "\" is formatted incorrectly." +
						"\n\"<word>\" is missing.");
				} else {
					oddWord = false;
				}
			}

			if (nextWord.equals("/def")) {
				if(!oddDef) {
				throw new BadFormatException("Error: File \"" + args + "\" is formatted incorrectly." +
						"\n\"<def>\" is missing.");
				} else {
					oddDef = false;
				}
			}

			if (nextWord.equals("/vocab")) {
				oddVocab = false;
			}
		}
		
		if(oddVocab) {
			throw new BadFormatException("Error: File \"" + args + "\" is formatted incorrectly." +
					"\n\"<vocab>\" is missing.");
			}
		
//		System.out.println(this);
		
		return true;
	}
}
