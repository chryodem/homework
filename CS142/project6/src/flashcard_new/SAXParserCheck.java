package flashcard_new;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SAXParserCheck{

	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter XML file name:");
		String xmlFile = bf.readLine();
		SAXParserCheck par = new SAXParserCheck(xmlFile);
	}

	public SAXParserCheck(String str){
		try{
			File file = new File(str);
			if (file.exists()){
				XMLReader reader = XMLReaderFactory.createXMLReader();
				reader.parse(str);
				System.out.println(str + " is well-formed!");
				try {

					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
					Document doc = docBuilder.parse (new File(str));

					// normalize text representation
					doc.getDocumentElement ().normalize ();
					Element rootElement = doc.getDocumentElement();

					System.out.println ("Root element of the doc is " + 
							doc.getDocumentElement().getNodeName() + "\n");
					NodeList nodeList = doc.getElementsByTagName("vocab");

					for(int index=0; index < nodeList.getLength(); index++)
					{
						Node node = nodeList.item(index);

						if (node.getNodeType() == Node.ELEMENT_NODE)
						{
							Element element = (Element) node;

							NodeList nameNode = element.getElementsByTagName("word");
							NodeList nameNode1 = element.getElementsByTagName("def");

							for(int iIndex=0; iIndex< nameNode.getLength(); iIndex++)
							{

								if (nameNode.item(iIndex).getNodeType() ==Node.ELEMENT_NODE)
								{
									Element nameElement = (Element) nameNode.item(iIndex);
									System.out.println("Word = " +nameElement.getFirstChild().getNodeValue().trim());
									Element nameElement1 = (Element) nameNode1.item(iIndex);
									System.out.println("Defenition = " +nameElement1.getFirstChild().getNodeValue().trim()+ "\n");
								}
							}

						}

					}


				}catch (SAXParseException err) {
					System.out.println ("** Parsing error" + ", line " 
							+ err.getLineNumber () + ", uri " + err.getSystemId ());
					System.out.println(" " + err.getMessage ());

				}catch (SAXException e) {
					Exception x = e.getException ();
					((x == null) ? e : x).printStackTrace ();

				}catch (Throwable t) {
					t.printStackTrace ();
				}
				System.exit (0);
			}

		
		else{
			System.out.println("File not found: " + str);
		}
	}
	catch (SAXException sax){
		System.out.println(str + " isn't well-formed");
	}
	catch (IOException io){
		System.out.println(io.getMessage());
	}
}
}