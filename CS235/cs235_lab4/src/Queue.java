import java.util.ArrayList;


public class Queue {
	public ArrayList<Integer> queue = new ArrayList<Integer>();
	
	
	public void push(int whatToAdd){
		queue.add(whatToAdd);
	}
	
	public int showTop(){
		int top = queue.get(0);
		return top;
	}
	
	public int showSize(){
		int size = queue.size();
		return size;
	}
	
	public int removeFrom(){
		return queue.remove(0);
		
	}

}
