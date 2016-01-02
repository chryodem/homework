package flashcard;

import java.io.File;
import java.io.IOException;

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
	//private Word[] theWords;

	public boolean readData(String fileName) {
		try {
			File file = new File(fileName);
			if (file.exists()) {
				XMLReader reader1 = XMLReaderFactory.createXMLReader();
				reader1.parse(fileName);
				System.out.println(fileName + " is well-formed!");
				try {

					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory
							.newDocumentBuilder();
					Document doc = docBuilder.parse(new File(fileName));

					// normalize text representation
					doc.getDocumentElement().normalize();
					Element rootElement = doc.getDocumentElement();

					System.out.println("Root element of the doc is "
							+ doc.getDocumentElement().getNodeName() + "\n");
					NodeList nodeList = doc.getElementsByTagName("vocab");

					for (int index = 0; index < nodeList.getLength(); index++) {
						Node node = nodeList.item(index);

						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) node;

							NodeList nameNode = element.getElementsByTagName("word");
							NodeList nameNode1 = element.getElementsByTagName("def");

							for (int iIndex = 0; iIndex < nameNode.getLength(); iIndex++) {

								if (nameNode.item(iIndex).getNodeType() == Node.ELEMENT_NODE) {
									Element nameElement = (Element) nameNode
											.item(iIndex);
									System.out.println("Word = "
											+ nameElement.getFirstChild()
													.getNodeValue().trim());
									Element nameElement1 = (Element) nameNode1
											.item(iIndex);
									System.out.println("Defenition = "
											+ nameElement1.getFirstChild()
													.getNodeValue().trim()
											+ "\n");
								}
							}

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
				System.out.println("File not found: " + fileName);
			}
		} catch (SAXException sax) {
			System.out.println(fileName + " isn't well-formed");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		return true;
	}
}
