import java.io.*;
import java.util.Scanner;

public class ParsingExample {

  public static void main(String... aArgs) throws FileNotFoundException {
	  ParsingExample parser = new ParsingExample("words.rtml");
    parser.processLineByLine();
    log("Done.");
  }
  
  public ParsingExample(String aFileName){
    fFile = new File(aFileName);  
  }
  
  public final void processLineByLine() throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileReader(fFile));
    try {
      while ( scanner.hasNextLine() ){
        processLine( scanner.nextLine() );
      }
    }
    finally {
      
      scanner.close();
    }
  }
  
  protected void processLine(String aLine){
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter("<word>");
    //scanner.useDelimiter("</word>");
    //scanner.useDelimiter("<");
    if ( scanner.hasNext() ){
      String firstValue = scanner.next();
      //String secondValue = scanner.next();
      //String thirdValue = scanner.next();
      //String fourthValue = scanner.next();
      //String fifthValue = scanner.next();
      
      log(quote("First value is: " + firstValue.trim()) /*+  ", and second value is: " + secondValue.trim()+ ", and third value is: " +
      thirdValue.trim() + ", fourth value is: " + fourthValue.trim() + ", and last value is: "+ fifthValue.trim());
      //log("Name is : " + quote(name.trim()) + ", and Value is : " + quote(value.trim()) */ );
    }
    else {
      log("Empty or invalid line. Unable to process.");
    }
  }
  
  private final File fFile;
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
} 
