/**
 * 
 * 			Simple Linked List class with get, push-to-front, push-to-back, and toString functionality.
 * 
 * 			@author lukeg
 * 			@param <K> Type of Object to store
 * 			@since	Feb. 21, 2020
 * 
 */

public class MyLinkedList<K>{

	private class Node
	{
		/**
		 * Data stored in the list
		 */
		public K item;
		
		/**
		 * Reference to next item in the list. Will equal null if last node in the list
		 */
		public Node nextNode;
	}
	
	

	private Node headNode;// reference to first node. 
	private Node tailNode; // reference to last node.
	private int size; // size of list
	
	/**
	 * Default constructor creates an empty SimpleList object
	 */
	public MyLinkedList()
	{
		headNode = null;
		tailNode = null;
		size = 0;
	}
	
	/**
	 * Constructor to create a linked list with one item
	 */
	public MyLinkedList(K item)
	{
		Node firstNode = new Node();
		firstNode.item = item;
		headNode = firstNode;
		tailNode = firstNode;
		size = 1;
		
	}
	
	public int size() 
	{
		return size;
	}
	

	/**
	 * Get item/data at index n of the list.
	 * @param n index of item to get
	 * @return item data at index n
	 */
	public K get(int n)
	{
	    if(n < 0 || n >= size)
	    {
	        System.err.println("\n Illegal Access. Program Terminates...");
	        System.exit(1);
	    }
 
	    if(n == size - 1)
	    	return tailNode.item;
	    
	    Node p = headNode;
	    for(int i = 0; i < n; i++)
	    {
	    	p = p.nextNode;
	    	
	    }
	    return p.item;
	    
	}
	

	
	

	/**
	 * Adds one item to the end of the list.
	 * @param item data to add to end of the list
	 */
	public void pushBack(K item)
	{
		Node newNode = new Node();
		newNode.item = item;

		if(headNode == null) //list is empty.
		{
			newNode.nextNode = headNode;
			headNode = newNode;
			tailNode = newNode;

		}
		else
		{

			
			tailNode.nextNode = newNode;
			newNode.nextNode = null;
			tailNode = newNode;
			
		}
		size++;
	}
	
	/**
	 * Adds one item to the front of the list
	 * @param item data to add to the front of the list
	 */
	public void pushFront(K item) 
	{
		Node newNode = new Node();

		newNode.item = item;
		newNode.nextNode = headNode;

		headNode = newNode;
		size++;
		
		if(tailNode == null)
			tailNode = newNode;
		
	}
	

	/**
	 * Prints the items (in order) in the list
	 * @param <K> Stored Object type
	 * @param list list to print to System.out
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
	    for(int i = 0; i < this.size(); i++)
	        sb.append(this.get(i) + " ");
	    
	    if(sb.length() > 0)
	    	sb.deleteCharAt(sb.length()-1); // remove last space if list is not empty
	    
	    return sb.toString();
	}
	
	

}
