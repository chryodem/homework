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
		System.out.println(storeAll);
		
		//return vocabWord + "\n" + definition + "\n";
		
		return storeAll;
	}

	public boolean readData(String reader) {
		try {
			File file = new File(reader);
			if (file.exists()) {
				XMLReader reader1 = XMLReaderFactory.createXMLReader();
				reader1.parse(reader);
				System.out.println(reader + " is well-formed!");
				try {

					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory
							.newDocumentBuilder();
					Document doc = docBuilder.parse(new File(reader));

					// normalize text representation
					doc.getDocumentElement().normalize();
					//Element rootElement = doc.getDocumentElement();

					System.out.println("Root element of the doc is "
							+ doc.getDocumentElement().getNodeName() + "\n");
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
									System.out.println("Word = "
											+ nameElement.getFirstChild()
													.getNodeValue().trim());
									String stringWord =  nameElement.getFirstChild()
									.getNodeValue().trim();
									Element nameElement1 = (Element) nameNode1
											.item(iIndex);
									System.out.println("Defenition = "
											+ nameElement1.getFirstChild()
													.getNodeValue().trim()
											+ "\n");
									String stringDefinition = nameElement1.getFirstChild()
									.getNodeValue().trim();
									
									Word w = new Word(stringWord, stringDefinition);
									list.add(w);
								}
							}
							theWords = list.toArray(theWords);

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
				System.exit(0);
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
