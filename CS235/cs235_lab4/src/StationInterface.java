
public interface StationInterface
{
	//Part 1--------------------------------------------------------------
	/**
	 * Let another car arrive at the station and become the current car.
	 * Do not allow a new car to arrive if any of the following conditions apply:
	 * 1.	There is already a current car
	 * 2.	The new car's ID already exists in any structure
	 * 3.	The new car's ID is negative
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @param car the ID of the car arriving at the station
	 * @return true if the car successfully arrived; false otherwise
	 */
	public boolean addToStation(int car);

	/**
	 * Returns the ID of the current car.
	 * Return -1 if there is no current car.
	 * 
	 * @return the ID of the current car; -1 if there is no current car
	 */
	public int showCurrentCar();
	
	/**
	 * Removes the current car from the station.
	 * Does nothing if there is no current car.
	 *
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the current car successfully left; false otherwise
	 */
	public boolean removeFromStation();
	
	//Part 2--------------------------------------------------------------
	/**
	 * Adds the current car to the stack.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the stack is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the stack; false otherwise
	 */
	public boolean addToStack();
	
	/**
	 * Removes the first car in the stack and makes it the current car.
	 * Does nothing if there is already a current car or if the stack already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the stack; false otherwise
	 */
	public boolean removeFromStack();

	/**
	 * Returns the ID of the first car in the stack.
	 * 
	 * @return the ID of the first car in the stack; -1 if the stack is empty
	 */
	public int showTopOfStack();
	
	/**
	 * Returns the number of cars in the stack.
	 * 
	 * @return the number of cars in the stack
	 */
	public int showSizeOfStack();

	//Part 3--------------------------------------------------------------
	/**
	 * Adds the current car to the queue.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the queue is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the queue; false otherwise
	 */
	public boolean addToQueue();
	
	/**
	 * Removes the first car in the queue and makes it the current car.
	 * Does nothing if there is already a current car or if the queue already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the queue; false otherwise
	 */
	public boolean removeFromQueue();

	/**
	 * Returns the ID of the first car in the queue.
	 * 
	 * @return the ID of the first car in the queue; -1 if the queue is empty
	 */
	public int showTopOfQueue();
	
	/**
	 * Returns the number of cars in the queue.
	 * 
	 * @return the number of cars in the queue
	 */
	public int showSizeOfQueue();

	//Part 4--------------------------------------------------------------
	/**
	 * Adds the current car to the deque on the left side.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the deque is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the deque; false otherwise
	 */
	public boolean addToDequeLeft();

	/**
	 * Adds the current car to the deque on the right side.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the deque is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the deque; false otherwise
	 */
	public boolean addToDequeRight();
	
	/**
	 * Removes the leftmost car in the deque and makes it the current car.
	 * Does nothing if there is already a current car or if the deque already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the deque; false otherwise
	 */
	public boolean removeFromDequeLeft();
	
	/**
	 * Removes the rightmost car in the deque and makes it the current car.
	 * Does nothing if there is already a current car or if the deque already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the deque; false otherwise
	 */
	public boolean removeFromDequeRight();
	
	/**
	 * Returns the ID of the leftmost car in the deque.
	 * 
	 * @return the ID of the leftmost car in the deque; -1 if the deque is empty
	 */
	public int showTopOfDequeLeft();
	
	/**
	 * Returns the ID of the rightmost car in the deque.
	 * 
	 * @return the ID of the rightmost car in the deque; -1 if the deque is empty
	 */
	public int showTopOfDequeRight();
	
	/**
	 * Returns the number of cars in the deque.
	 * 
	 * @return the number of cars in the deque
	 */
	public int showSizeOfDeque();

	//Part 5--------------------------------------------------------------
	/**
	 * Returns the total number of accepted operations performed at the station.
	 * See the lab specs for details on which operations are acceptable.
	 * 
	 * @return the number of accepted operations performed
	 */
	public int showAcceptedOperations();

	/**
	 * Returns the total number of rejected operations performed at the station.
	 * See the lab specs for details on which operations are acceptable.
	 * 
	 * @return the number of rejected operations performed
	 */
	public int showRejectedOperations();	
}
