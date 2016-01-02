
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		deque myDeck = new deque();
		
	//	System.out.println(myStack.isEmpty());
		
		myDeck.addToLeft(9);
		//System.out.println(myDeck.showLeft());
		myDeck.addToRight(19);
	//	System.out.println(myDeck.showLeft());
	//	System.out.println(myDeck.showRight());
		myDeck.addToRight(21);
		myDeck.addToLeft(34);
		myDeck.addToLeft(46);
		myDeck.addToRight(57);
	//	System.out.println(myDeck.showLeft());
	//	System.out.println(myDeck.showRight());
		
		
		System.out.println(myDeck.dequeue);
		//myStack.push(myNumber);
		
		
		// TODO Auto-generated method stub
		
	//	int removed = myStack.pop();
	//	System.out.println(removed);
	//	System.out.println(myStack.peek());

	}

}
