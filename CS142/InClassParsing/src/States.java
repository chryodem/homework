import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Formatter;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class States {



	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse (new File("words.rtml"));
        

        NodeList nodeList = doc.getElementsByTagName("word");
         
        for(int index=0; index < nodeList.getLength(); index++)
        {
                Node node = nodeList.item(index);
         
        	if (node.getNodeType() == Node.ELEMENT_NODE)
        	{
        	        Element element = (Element) node;
         
        		NodeList nameNode = element.getElementsByTagName("word");
         
        		for(int iIndex=0; iIndex< nameNode.getLength(); iIndex++)
        		{
        		        if (nameNode.item(iIndex).getNodeType() ==Node.ELEMENT_NODE)
        			{
        				Element nameElement = (Element) nameNode.item(iIndex);
        				System.out.println("Word = " +nameElement.getFirstChild().getNodeValue().trim());
        			}
        	        }
                }
        }

		
	
		/*
		// your input file with city, state values
		File file = new File("states.txt");

		// data structure to hold mapping of state to list of cities, sorted by state

		// scan file by line and populate data structure
		Scanner scanner = new Scanner(file).useDelimiter("\\n");
		while (scanner.hasNext()) {
		    String line = scanner.next();

		    // only process lines with a comma
		    if (line.contains(",")) {
		        // split the line on the comma and extract the city and state
		        // note this won't work properly if the city has a comma in it
		        String[] parts = line.split(",");
		        String city = parts[0].trim();
		        String state = parts[1].trim();

		        // if the state doesn't exist in the map yet, create it
		        List<String> cities = map.get(state);
		        if (cities == null) {
		            cities = new ArrayList<String>();
		            map.put(state, cities);
		        }

		        // add the city to the list for the state if it's not in it yet
		        if (!cities.contains(city)) {
		            cities.add(city);
		        }
		    }
		}

		// iterate over the states for output
		for (String state : map.keySet()) {
		    // build up a string for each state with the list of cities
		    StringBuilder sb = new StringBuilder();

		    // start with the state
		    sb.append(state + ": ");

		    // now append the cities
		    List<String> cities = map.get(state);
		    for (String city : cities) {
		        sb.append(city + ", ");
		    }

		    // remove the last comma
		    sb.delete(sb.length() - 2, sb.length());

		    // print out the finished line
		    String output = sb.toString();
		    System.out.println(output);
		}
		 */

	}
}
