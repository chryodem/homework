package flashcard_new;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class Data{

    public static void main (String argv []) throws IOException {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the name of the file you need to parse: ");
		String string = bf.readLine().toLowerCase();
		String stringWord = "words.rtml";
		String stringWordsShort= "words_short.rtml";
		String stringWordsEmpty= "words_empty.rtml";
		
		if(string.equals(stringWord) || string.equals(stringWordsShort) || string.equals(stringWordsEmpty)){
			System.out.println("Success! Reading file...\n");
			
    	
    	
    try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(string));

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
		else {
			System.out.println("Incorrect name!");
		}

    }//end of main


}