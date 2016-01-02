
public class Station implements StationInterface {

	int currentCar = -1; // no current car
	int newCar = 0, accepted, rejected;

	Stack myStack = new Stack();
	Queue myQueue = new Queue();
	deque myDeck = new deque();

	@Override
	public boolean addToStation(int car) {
		if(currentCar == -1){
			if(car<0){
				rejected++;
				return false;
			}

			if(myStack.stack.contains(car) || myDeck.dequeue.contains(car) || myQueue.queue.contains(car)){
				rejected++;
				return false; }

			System.out.println("Request here and show what's passed in: " + car);
			accepted++;
			currentCar = car;
			return true;
		}
		else{
			rejected++;
			return false;
		}

	}

	@Override
	public int showCurrentCar() {
		if(currentCar == -1){
			return -1;
		}
		return currentCar;
	}

	@Override
	public boolean removeFromStation() {
		if(currentCar == -1){
			rejected++;
			return false;
		}
		currentCar =-1;
		accepted++;
		return true;
	}

	@Override
	public boolean addToStack() {
		if(myStack.stack.size()>4){
			rejected++;
			return false;
		}
		else{
			if(currentCar == -1 ){

				rejected++;
				return false;
			}

			myStack.push(currentCar);
			currentCar = -1;
			accepted++;
			return true;

		}
	}

	@Override
	public boolean removeFromStack() {
		if(myStack.stack.isEmpty()==true || currentCar!=-1){
			rejected++;
			return false;
		}
		currentCar = myStack.pop();
		accepted++;
		return true;
	}

	@Override
	public int showTopOfStack() {
		if(myStack.stack.isEmpty()==true){
			return -1;
		}

		return myStack.peek();
	}

	@Override
	public int showSizeOfStack() {
		return myStack.stack.size();
	}

	@Override
	public boolean addToQueue() {
		if(myQueue.queue.size()>4){
			rejected++;
			return false;
		}
		if(currentCar == -1){
			rejected++;
			return false;
		}
		myQueue.push(currentCar);
		currentCar = -1;
		accepted++;
		return true;

	}

	@Override
	public boolean removeFromQueue() {
		if(myQueue.queue.isEmpty()== true || currentCar!=-1){
			rejected++;
			return false;
		}
		currentCar = myQueue.queue.remove(0);
		accepted++;
		return true;
	}

	@Override
	public int showTopOfQueue() {
		if(myQueue.queue.isEmpty()==true){
			return -1;
		}
		return myQueue.showTop();
	}

	@Override
	public int showSizeOfQueue() {
		return myQueue.queue.size();
	}

	@Override
	public boolean addToDequeLeft() {
		if(myDeck.dequeue.size()>4){
			rejected++;
			return false;
		}
		if(currentCar == -1){
			rejected++;
			return false;
		}

		//myDeck.addToLeft(currentCar);
		myDeck.dequeue.add(0, currentCar);
		currentCar = -1;
		accepted++;
		return true;
	}

	@Override
	public boolean addToDequeRight() {
		if(myDeck.dequeue.size()>4){
			rejected++;
			return false;
		}
		
		if(currentCar == -1){
			rejected++;
			return false;
		}
		myDeck.addToRight(currentCar);
		currentCar = -1;
		accepted++;
		return true;
	}

	@Override
	public boolean removeFromDequeLeft() {
		if(myDeck.dequeue.isEmpty()==true || currentCar!= -1){
			rejected++;
			return false;
		}
		currentCar = myDeck.removeLeft();
		accepted++;
		return true;
	}

	@Override
	public boolean removeFromDequeRight() {
		if(myDeck.dequeue.isEmpty()==true || currentCar!= -1){
			rejected++;
			return false;
		}
		currentCar = myDeck.removeRight();
		accepted++;
		return true;
	}

	@Override
	public int showTopOfDequeLeft() {
		if(myDeck.dequeue.isEmpty()==true){
			return -1;
		}
		return myDeck.dequeue.get(0);//showLeft();
	}

	@Override
	public int showTopOfDequeRight() {
		if(myDeck.dequeue.isEmpty()==true){
			return -1;
		}
		return myDeck.showRight();
	}

	@Override
	public int showSizeOfDeque() {
		return myDeck.dequeue.size();
	}

	@Override
	public int showAcceptedOperations() {

		return accepted;
	}

	@Override
	public int showRejectedOperations() {

		return rejected;
	}

}
