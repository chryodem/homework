import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Mimic implements MimicInterface {
	ArrayList<String> fileInput;
	ArrayList<String> words;
	MapsEntries mE;

	@Override
	public void createMap(String input) {
		Scanner scan = new Scanner (input);
		fileInput = new ArrayList<String>();
		words = new ArrayList<String>();
		
		while(scan.hasNext()){
			fileInput.add(scan.next());
		}
		
		mE = new MapsEntries(words);
		
		
		while(fileInput.size()>1){
			String suffix;
			String prefix = fileInput.get(0)+ " "+fileInput.get(1);
			
			if (fileInput.size() == 2){
				suffix = "THE_END";
				mE.mapCreate(prefix, suffix);
				break;
			}
			
			suffix = fileInput.get(2);
			mE.mapCreate(prefix, suffix);
			fileInput.remove(0);
		}

	}

	@Override
	public List<String> getSuffixList(String prefix) {
		mE = new MapsEntries(words);
		
		ArrayList<String> suffix = mE.getSuffix(prefix);
		
		return suffix;
	}

	@Override
	public String generateText() {
		mE = new MapsEntries(words);
	    String text = mE.getRandText();
		int length = text.length();
		String shortenedText = text.substring(0, length-8);
		return shortenedText;
	}
}