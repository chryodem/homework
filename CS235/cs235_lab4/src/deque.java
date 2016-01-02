import java.util.ArrayList;


public class deque {
	public ArrayList<Integer> dequeue = new ArrayList<Integer>();
	
	public int showSize(){
		int size = dequeue.size();
		return size;
	}
	
	public void addToLeft(int whatToAdd){
		dequeue.add(0, whatToAdd);
		
	}
	public void addToRight(int whatToAdd){
		dequeue.add(whatToAdd);
	}
	
	public int showLeft(){
		int left = dequeue.get(0);
		return left;
	}
	
	public int showRight(){
		int right = dequeue.get((dequeue.size()-1));
		
		return right;
	}
	
	public int removeLeft(){
		int left  = dequeue.remove(0);
		return left;
		
	}
	public int removeRight(){
		
		int  right = dequeue.remove((dequeue.size()-1));
		return right;
	}

}
