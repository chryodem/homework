package flashcard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Flashcard {
	private Word theWords[];
	private ArrayList<Word> Words;
	
	public Flashcard()
	{
		theWords = new Word[0];		
	}
	
	public String toString()
	{
		String storeAll = "";
		for(int i=0; i<theWords.length; i++)
		{
			storeAll += theWords[i].toString()+ "\n";
		}
		//System.out.println(storeAll);
		
		return storeAll;
	}
	public ArrayList<Word> getArrayList(){
		return Words;
	}
	public String[][] conversion() //creates 2d array
	{
		String[][] words2D = new String[theWords.length][2]; //initializes the 2d array with theWords, and 2 (columns)

		for (int i = 0; i < theWords.length; i++)

		{
			words2D[i][0] = theWords[i].getVocabWord(); //stores all of the words values in "i" and "0"
			words2D[i][1] = theWords[i].getDefinition(); //stores all of the defs value in "i" and "1"
			//System.out.println(words2D[i][0]); // test to make sure words2D is working properly

		}
		return words2D; //returns the populated array

	}

	public boolean readData(String reader) {
		try {
			File file = new File(reader);
			if (file.exists()) {
				XMLReader reader1 = XMLReaderFactory.createXMLReader();
				reader1.parse(reader);
				//System.out.println(reader + " is well-formed!");
				try {

					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory
							.newDocumentBuilder();
					Document doc = docBuilder.parse(new File(reader));

					// normalize text representation
					doc.getDocumentElement().normalize();

					NodeList nodeList = doc.getElementsByTagName("vocab");
					ArrayList <Word> list = new ArrayList<Word>();

					for (int index = 0; index < nodeList.getLength(); index++) {
						Node node = nodeList.item(index);

						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) node;

							NodeList nameNode = element.getElementsByTagName("word");
							NodeList nameNode1 = element.getElementsByTagName("def");

							for (int iIndex = 0; iIndex < nameNode.getLength(); iIndex++)
							{

								if (nameNode.item(iIndex).getNodeType() == Node.ELEMENT_NODE) 
								{
									Element nameElement = (Element) nameNode
											.item(iIndex);
									String stringWord =  nameElement.getFirstChild()
									.getNodeValue().trim();
									Element nameElement1 = (Element) nameNode1
											.item(iIndex);
									String stringDefinition = nameElement1.getFirstChild()
									.getNodeValue().trim();
									
									Word w = new Word(stringWord, stringDefinition);
									list.add(w);
								}
							}
							theWords = list.toArray(theWords); //populates the array list
							Words=list;

						}

					}

				} catch (SAXParseException err) {
					System.out.println("** Parsing error" + ", line "
							+ err.getLineNumber() + ", uri "
							+ err.getSystemId());
					System.out.println(" " + err.getMessage());

				} catch (SAXException e) {
					Exception x = e.getException();
					((x == null) ? e : x).printStackTrace();

				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			else {
				System.out.println("File not found: " + reader);
			}
		} catch (SAXException sax) {
			System.out.println(reader + " isn't well-formed");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		return true;
	}
	
}
