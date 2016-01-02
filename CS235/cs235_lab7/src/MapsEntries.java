import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MapsEntries {
	
	ArrayList<mapEntries> map;
	String randomline;
	//constructor for access
	MapsEntries(ArrayList map){
		this.map = map;
	}
	
	public void mapCreate(String key, String suffix){
		boolean check = false;
		ArrayList<String> Suffix = new ArrayList<String>();
		
		for(int i = 0; i < map.size();i++){
			
			if(key.equals(map.get(i).key)){
				map.get(i).addSuffix(suffix);
				check = true;
				
			}
		}
		
		//if there is no values
		if(check == false){
			map.add(new mapEntries(key, Suffix));
			map.get(map.size()-1).addSuffix(suffix);
		}
	}
	
	public ArrayList<String> getSuffix(String key){
		ArrayList<String> suffix = null;
		for(int i = 0; i < map.size();i++){
			if(key.equals(map.get(i).key)){
				suffix = map.get(i).value;
			}
		}
		return suffix;
	}
	
	public String getRandSuffix(String key){
		String suffix = null;
		Random rand = new Random();
		
		
		for(int i = 0; i < map.size();i++){
			if(key.equals(map.get(i).key)){
				
				int index = rand.nextInt(map.get(i).value.size());
				suffix = map.get(i).value.get(index).toString();
				
			}
		}
		return suffix;
		
	}
	
	public String getRandText(){
		String current = map.get(0).key;
		String text = recursive(current);
		
		return text;
	}
	
	
	public String recursive(String current){
		
		String generate = "";
		ArrayList<String> curse = new ArrayList<String>();
		Scanner scan = new Scanner(current);
		
		while(scan.hasNext()){
			curse.add(scan.next());
		}
		
		while(!(curse.get(curse.size()-1).equals("THE_END"))){
			String prefix = curse.get(curse.size()-2)+ " " + curse.get(curse.size()-1);
			String suffix = getRandSuffix(prefix);
			
			curse.add(suffix);
		}
		
		for(int i =0;i<curse.size();i++){
			generate+= curse.get(i) + " ";
		}
		
		return generate;
	}
	
	private class mapEntries{
		String key;
		ArrayList value;
		
		//constructor for the method
		mapEntries(String key, ArrayList value){
			this.key = key;
			this.value = value;
		}
		
		public void addSuffix(String suffix){
			value.add(suffix);
		}
		
	}

}
