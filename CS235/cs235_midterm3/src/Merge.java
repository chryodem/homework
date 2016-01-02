import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Merge implements MergeInterface {
	
private	boolean sorted = false;
private	boolean nothing_to_add = false;
private	int read_write,number_of_files, position_for_fileNames, chunk;
private	String[] portion, fileNames, second_level; //portion - main-memory array, second level where the values will be sorted
private String lastFile;
private PrintWriter outSecondLevel;

	@Override
	public String sort(String file_name, int record_count,int main_memory_array_size) {
		number_of_files = 0; 
		read_write = 0;
		position_for_fileNames=0;
		sorted = false;
		nothing_to_add=false;
		if(file_name==null){
			sorted = false;
		}
		int totalFiles = 0;
		if(record_count%main_memory_array_size==0){
			totalFiles=record_count/main_memory_array_size;
		}
		else{
			totalFiles=record_count/main_memory_array_size+1;
		}
		
		portion = new String[main_memory_array_size]; //set the size of the array
		second_level = new String[main_memory_array_size];//set the size of the second level array
		fileNames = new String[totalFiles*2+1]; //set the size of the array
		PrintWriter out; //create the file to store everything sorted
		try{
			Scanner in = new Scanner(new File(file_name)); //scanning file in
			//generates the number of files to be created for the first part of sorting
			int i = 0;
			while(in.hasNext()){ 
				portion[i]=in.next();
				i++;
				if(i==main_memory_array_size || in.hasNext()==false){
					createFile(portion);
					read_write++;
					portion = new String[main_memory_array_size]; 
					//restarting the system
					i=0;
				}
			} //end of while
			
			lastFile = "sorted.txt";
			
			out = new PrintWriter(new File(lastFile));//creates the files where sorted values will be placed
			fileNames[fileNames.length-1]=lastFile;
			portion = new String[main_memory_array_size];//make sure the array is cleared
			
			chunk = main_memory_array_size/number_of_files;
			
			readIntoMainArray(totalFiles); //reads the first parts of chunks into the main array
			outSecondLevel = new PrintWriter(new File(fileNames[fileNames.length-1]));
			buildSecondArray(); //starts building and sorting the second array
			
			return fileNames[fileNames.length-1];
			
		}//end of try
		catch(Exception e){
		//	e.printStackTrace();
			sorted = false;
			return null;
		}
		
	}

	private void buildSecondArray() {
		int smallest = findSmallest();
		int i =0;
		while(i<second_level.length){
			if(smallest==-1){
				nothing_to_add=true;
				WriteSecondLevelArray();
				return;
			}
			second_level[i]=portion[smallest];
			portion[smallest]=null;
			checkChunks(smallest); 
			//checks the chunks to make sure there is always something in there
			
			smallest = findSmallest();
			i++;
			if(checkSecondLevelArray()){
				WriteSecondLevelArray();
				second_level = new String[portion.length];
				i = 0;
			}
		}
	}
private void WriteSecondLevelArray() {
	try{
		for(int i = 0; i < second_level.length;i++){
			if(second_level[i]!=null){
				outSecondLevel.println(second_level[i]);
			}
		}
		if(nothing_to_add){
			read_write++;
			sorted = true;
			outSecondLevel.close();
		}
	}
	catch(Exception e){
		
	}
	}

private boolean checkSecondLevelArray() {
	int number_of_nulls = 0;
		for(int i =0; i< second_level.length;i++){
			if(second_level[i]==null){
				number_of_nulls++;
			}
		}
		if(number_of_nulls==0){
			
			return true;
		}
		else{
			return false;
		}
	}

//checks chunks for exhaustion
	private void checkChunks(int smallest) {
		int number_of_nulls = 0;
		int starting_place = (smallest/chunk)*chunk;
		for(int i = starting_place;i<starting_place+chunk;i++){
			if(portion[i]==null){
				number_of_nulls++;
			}
		}
		if(number_of_nulls==chunk){
			
			findReplacement(smallest,starting_place);
		}
	}
//looks for replacement values in the exhausted chunks
	private boolean findReplacement(int smallest, int starting_place) {
		//add a reader to make sure that the number of items in the file is == or larger than chunk
		String file_need_to_Read = fileNames[(smallest/chunk)+fileNames.length/2];
		StringTokenizer tok;
		Scanner scan,in;
		int number_of_entries_in_file = 0;
		PrintWriter out;
		StringBuilder sb = new StringBuilder();
		try{
			in = new Scanner(new File(file_need_to_Read));
			while(in.hasNext()){
				in.next();
				number_of_entries_in_file++;
				
			}
			read_write++;
			if(number_of_entries_in_file<=chunk){
				scan = new Scanner(new File(file_need_to_Read)); //do the scanner'
				if(number_of_entries_in_file==0){
					CheckPortionArray();
					return false;
				}
				else{
					
					while(scan.hasNext()){
						for(int i = starting_place;i<starting_place+number_of_entries_in_file;i++){
							if(portion[i]==null){
								portion[i]=scan.next();
							}
						}
					}//end of while
					out = new PrintWriter(new File(file_need_to_Read));
					read_write++;
					out.close();
					
					return true;
				}
				
			}
			else {//if(number_of_entries_in_file>chunk){
				scan = new Scanner(new File(file_need_to_Read));
				
					while(scan.hasNext() ){
						for(int i = starting_place;i<starting_place+chunk;i++){
							if(portion[i]==null){
								portion[i]=scan.next();
								
							}
						}
							sb.append(scan.next() + " ");
					}//end of while
					
					String result = sb.toString();
					tok = new StringTokenizer(result);
					out = new PrintWriter(new File(file_need_to_Read));
					while(tok.hasMoreTokens()){
						out.println(tok.nextToken());
					}
					read_write++;
					out.close();
					return true;
			}
		}
		catch(Exception e){
			return false;
		}
	}
	
	
private void CheckPortionArray() {
	int num_of_nulls = 0;
	for(int i = 0; i <portion.length;i++){
		if(portion[i]==null){
			num_of_nulls++;
		}
	}
	if(num_of_nulls==(portion.length)){
		nothing_to_add=true;
		WriteSecondLevelArray();
	}
	
}

//looks for the smallest value in the main array set starting from 0 always
	private int findSmallest() {
		int index = 0;
		String min = portion[index];
		if(min==null){
			int notNullIndex = findNextNotNull(0);
			if(notNullIndex==-1){
				sorted = false;
				return -1;
				
			}
			min = portion[notNullIndex];
		}
		for(int i =0;i<portion.length;i++){
			
			if(portion[i]!=null){
				if(min.compareTo(portion[i])>=0){ 
					min=portion[i];
					index = i;
					sorted=true;
				}
			}
		}
		return index;
	}
	
	//looks for the closest not null place to start comparing
	private int findNextNotNull(int i) {
		if(portion[i]!=null){
			return i;
		}
		else{
			if(i<portion.length){
				if(i+1<portion.length){
					return findNextNotNull(i+1);
				}
				else{
					return -1;
				}
			}
			else{
				return -1;
			}
		}
	}

	//reads into main array from the files
	//initially
	private void readIntoMainArray(int totalFiles) {
		Scanner reading;
		StringTokenizer tok; //= new StringTokenizer();
		StringBuilder sb = new StringBuilder();
		String reader;
		
			try {
				for(int j=0;j<totalFiles;j++){
					read_write++;
				reading = new Scanner(new File(fileNames[j]));
				int k = 0;
				while(k<chunk){
					reader = (reading.next());
					sb.append(reader + " ");
					k++;
				}//end of while
				//update location position for the next time when need to grab values from files
				UpdatePositionLocation(j);
				}
				
			} catch (Exception e) {
			}
			//addingValuesToMainArray(reading);
		
		reader = sb.toString();
		tok = new StringTokenizer(reader);
		int a = 0;
		while(tok.hasMoreTokens()){
			portion[a]=tok.nextToken();
			a++;
		}
		
	}

	private void UpdatePositionLocation(int j) {
		String file_to_Update=fileNames[j+fileNames.length/2];
		String file_to_Read = fileNames[j];
		Scanner reading;
		PrintWriter out;
		try{
			int i = 0;
			reading = new Scanner(new File(file_to_Read));
			out = new PrintWriter(file_to_Update);
			while(reading.hasNext()){
				if(i>=chunk){
					out.println(reading.next());
					i++;
				}else{
					reading.next();
					i++;
				}
				
			}
			read_write++;
			out.close();
		}
		catch(Exception e){
		}
	}

	private void createFile(String[] portion2) {
		for(int i = 0; i < portion2.length;i++){
			if(portion2[i]==null){
				portion2[i]="";
			}
		}
		String filesName = null;
		String positionInFile = null;
		Arrays.sort(portion2);
		PrintWriter out,outPosition;
		try {
			filesName = "data"+Integer.toString(number_of_files)+".txt";
			out = new PrintWriter(new File(filesName));
			for(int i =0; i<portion2.length;i++){
				if(portion2[i]!=""){
					out.println(portion2[i]);
				}
				else{
				}
			}
			read_write++;
			out.close();
			addFilesNameToArray(filesName); //adds the name of the created file to the array
			positionInFile = "position"+Integer.toString(number_of_files)+".txt";
			outPosition = new PrintWriter(new File(positionInFile));
			outPosition.println("0");
			read_write++;
			outPosition.close();
			addPositionFileToArray(positionInFile);
			number_of_files++; //increase the number of files
		} catch (Exception e) {
		}
	}

	//adds the files name to the array of file's names
	private void addFilesNameToArray(String filesName) {
		fileNames[position_for_fileNames]=filesName;
	}
	//add the position for the file name to the array
	private void addPositionFileToArray(String positionInFile) {
		fileNames[position_for_fileNames+(fileNames.length/2)]=positionInFile;
		position_for_fileNames++;
		
	}

	@Override
	public String[] reportIntermediateFileNames() {
		if(sorted){
			return fileNames;
		}
		else{
			return null;
		}
	}

	@Override
	public int reportReadsAndWrites() {
		if(sorted){
			return read_write;
		}
		else{
			return -1;
		}
	}

	@Override
	public void loadArray1All(String file_name, int chunk) {

	}

	@Override
	public void loadArray1Part(String file_name, int chunk, int write_index) {

	}

	@Override
	public void printArray1(String file_name) {

	}

	@Override
	public void printArray2(String file_name) {

	}

}
