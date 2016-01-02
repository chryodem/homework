package flashreader;

import java.io.File;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class FileReader{

    public static void main (String argv []){
    	 try {

             DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
             Document doc = docBuilder.parse (new File("inclass.xml"));

             NodeList nodeList = doc.getElementsByTagName("subelement");
             
             for(int index=0; index < nodeList.getLength(); index++)
             {
                     Node node = nodeList.item(index);
              
             	if (node.getNodeType() == Node.ELEMENT_NODE)
             	{
             	        Element element = (Element) node;
              
             		NodeList nameNode = element.getElementsByTagName("subsubelement");
              
             		for(int iIndex=0; iIndex< nameNode.getLength(); iIndex++)
             		{
             			
             				Element nameElement = (Element) nameNode.item(iIndex);
             				System.out.println("Subsubelement = " +nameElement.getFirstChild().getNodeValue().trim());
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
         //System.exit (0);
 		}
        

    }//end of main


