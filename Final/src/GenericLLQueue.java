import java.util.EmptyStackException;

public class GenericLLQueue<T> {
	
	/********************************************************************************
	 *	Description:	Instance variables needed.									* 
	 ********************************************************************************/
	
	private Node<T> top;
	private Node<T> bottom;
	private int size;
	
	/********************************************************************************
	 *	Description:	Constructor for Class.										* 
	 ********************************************************************************/
	
	public GenericLLQueue() {
		this.top = null;
		this.bottom = null;
		this.size = 0;
	}
	
	/********************************************************************************
	 *	Description:	Checks to see if Queue is empty.							* 
	 ********************************************************************************/
	
	public boolean isEmpty() {
		return top == null;
	}
	
	/********************************************************************************
	 *	Description:	Returns the size of the Queue.								* 
	 ********************************************************************************/
		
	public int size() {
		return size;
	}
	
	/********************************************************************************
	 *	Description:	This method adds a piece of data to the top of the Queue.	* 
	 ********************************************************************************/

	public void add(T data) {
		
		//Initialize new node with passed data...
		Node<T> newNode = new Node<T>(null, top, data);
		
		//Conditional for when queue is empty...
		if (isEmpty()) {
			
			//Set 'top' as the new Node, then 'bottom' a reference to the 'top'...
			top = newNode;
			bottom = top;
			
			//Increment size...
			size++;
			
		} else {
			
			//Assign the newNode to the position above the current 'top'...
			top.setTopLink(newNode);
			
			//Change 'top's reference...
			top = top.getTopLink();
			
			//Increment size...
			size++;
		
		}
		
	}
	
	/********************************************************************************
	 *	Description:	This method removes a node from the bottom of the Queue		* 
	 *																				*
	 *	Precondition:	The stack must not be empty.								*
	 *	Postcondition:	The stack will be shortened by 1.							*
	 *	Throws:			EmptyStackException()										*
	 ********************************************************************************/
	
	public T remove() {
		
		//Check for empty stack/queue...
		if (isEmpty()) {
			throw new EmptyStackException();
		} 
		
		//Store 'top' in removedObject...
		T removedObject = top.getData();
		
		if (size > 1) {
			
			//Initialize variable for loop...
			Node<T>cursor;
			Node<T>precursor = null;
			
			//Loop through the queue...
			for (cursor = top; cursor != null; cursor = cursor.getBottomLink()) {
				
				//Conditional statement for finding 'bottom' Node...
				if (cursor.getBottomLink() == null) {
					
					//Store data of bottom Node in removedObject to be returned later...
					removedObject = cursor.getData();
					
					//Change references...
					precursor.setBottomLink(null);
					bottom = cursor.getTopLink();
					
				}
				
				//Increment precursor....
				precursor = cursor;
				
			}
			
			//Decrease size by 1...
			size--;
			
		} else {
			
			//Set queue to null...
			top = null;
			bottom = null;
			
			//Decrement size if greater than zero...
			if (size > 0) {
				size--;
			}
			
		}
		
		//Return removed Object...
		return removedObject;
		
	}
	
	/********************************************************************************
	 *	Description:	This iterates through a Queue and returns a node at a 		*
	 *					'target' element.											* 
	 *																				*
	 *	Precondition:	The stack must not be empty.								*
	 *	Throws:			EmptyStackException()										*
	 ********************************************************************************/
	
	public Node<T> atPossition(int target) {
		
		//Check for empty stack/queue...
		if (isEmpty()) {
			throw new EmptyStackException();
		} 
		
		//Initialize needed variables for loop...
		Node<T> cursor;
		int possition = 0;
		
		//Loop through Queue...
		for (cursor = top; cursor != null; cursor = cursor.getBottomLink()) {
			
			//Conditional for finding 'target'...
			if (possition == target) {
				return cursor;
			} else {
				possition++;
			}
			
		}
		
		//Does not find 'target'...
		return null;
		
	}
	
	
	/********************************************************************************
	 *	Description:	toString method iterates through the Queue and visualizes	*
	 *					its contents.												* 
	 *																				*
	 *	Precondition:	The stack must not be empty.								*
	 *	Throws:			EmptyStackException()										*
	 ********************************************************************************/
	
	@Override
	public String toString() {
		
		//Initialize needed variables for loop...
		String accumulator = "-Top-\n";
		Node<T> cursor;

		//Loop through Queue and add data to accumulator...
		for (cursor = top; cursor != null; cursor = cursor.getBottomLink()) {
			accumulator += "| " + cursor.getData() + " |\n";
		}
		
		accumulator += "Bottom\n";
		
		//Return finalized accumulator...
		return accumulator;
	}
	
	/********************************************************************************
	 *	Description:	main method for testing Class.								*
	 ********************************************************************************/
	
	public static void main(String[] args) {
		
		//Initialize new Queue...
		GenericLLQueue<Integer> testQueue = new GenericLLQueue<>();

		//Add data and print...
		testQueue.add(1);
		testQueue.add(2);
		testQueue.add(3);
		System.out.println(testQueue.toString());
		
		//Add and remove and print...
		testQueue.add(4);
		testQueue.remove();
		System.out.println(testQueue.toString());
		
		//Add and remove and print...
		testQueue.add(5);
		testQueue.remove();
		System.out.println(testQueue.toString());

	}

}
