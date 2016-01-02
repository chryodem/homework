
public interface StationInterfaceExtra extends StationInterface
{
	//Input-Restricted Deque----------------------------------------------
	/**
	 * Adds the current car to the IRDeque on the left side.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the IRDeque is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the IRDeque; false otherwise
	 */
	public boolean addToIRDequeLeft();

	/**
	 * Removes the leftmost car in the IRDeque and makes it the current car.
	 * Does nothing if there is already a current car or if the IRDeque already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the IRDeque; false otherwise
	 */
	public boolean removeFromIRDequeLeft();
	
	/**
	 * Removes the rightmost car in the IRDeque and makes it the current car.
	 * Does nothing if there is already a current car or if the IRDeque already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the IRDeque; false otherwise
	 */
	public boolean removeFromIRDequeRight();
	
	/**
	 * Returns the ID of the leftmost car in the IRDeque.
	 * 
	 * @return the ID of the leftmost car in the IRDeque; -1 if the IRDeque is empty
	 */
	public int showTopOfIRDequeLeft();
	
	/**
	 * Returns the ID of the rightmost car in the IRDeque.
	 * 
	 * @return the ID of the rightmost car in the IRDeque; -1 if the IRDeque is empty
	 */
	public int showTopOfIRDequeRight();
	
	/**
	 * Returns the number of cars in the IRDeque.
	 * 
	 * @return the number of cars in the IRDeque
	 */
	public int showSizeOfIRDeque();

	//Output-Restricted Deque---------------------------------------------
	/**
	 * Adds the current car to the ORDeque on the left side.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the ORDeque is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the ORDeque; false otherwise
	 */
	public boolean addToORDequeLeft();
	
	/**
	 * Adds the current car to the ORDeque on the right side.  After this operation, there should be no current car.
	 * Does nothing if there is no current car or if the ORDeque is already full.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully added to the ORDeque; false otherwise
	 */
	public boolean addToORDequeRight();
	
	/**
	 * Removes the leftmost car in the ORDeque and makes it the current car.
	 * Does nothing if there is already a current car or if the ORDeque already empty.
	 * 
	 * THIS METHOD SHOULD CHANGE THE COUNT FOR ACCEPTED/REJECTED
	 * 
	 * @return true if the car is successfully removed from the ORDeque; false otherwise
	 */
	public boolean removeFromORDequeLeft();
	
	/**
	 * Returns the ID of the leftmost car in the ORDeque.
	 * 
	 * @return the ID of the leftmost car in the ORDeque; -1 if the ORDeque is empty
	 */
	public int showTopOfORDequeLeft();
		
	/**
	 * Returns the number of cars in the ORDeque.
	 * 
	 * @return the number of cars in the ORDeque
	 */
	public int showSizeOfORDeque();
}
