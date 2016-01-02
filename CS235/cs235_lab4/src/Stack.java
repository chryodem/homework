import java.util.ArrayList;


public class Stack {
	
	public ArrayList<Integer> stack = new ArrayList<Integer>();

	public int peek(){
		int	thisPeek =0;
		if(stack.isEmpty()==true){
			return thisPeek = -1;
		}
		else{
		thisPeek = stack.get(stack.size()-1);
		}
		
		return  thisPeek;
	} // end of peek
	
	public void push(int whatToPush){
		stack.add(whatToPush);
		
	}
	
	public int pop(){
		int pop = 0;
		if(stack.isEmpty()==false){
			pop = stack.get(stack.size()-1);
			stack.remove(stack.size()-1);
		}
		else{
			pop = -1;
		}
		return pop;
	}

}
