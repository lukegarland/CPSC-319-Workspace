/**
 * 
 * 			Simple Linked List class with basic add, get and set functionality.
 * 
 * 			
 * 			@author lukeg
 * 			@param <K> Type of Object to store
 * 			@since	Feb. 21, 2020
 * 
 */

public class MyLinkedList<K> {

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
	
	
	/**
	 * First node in the list
	 */
	private Node headNode;
	
	
	/*
	 * Tracks the current length of the list
	 */
	private int size;
	
	/**
	 * Default constructor creates an empty SimpleList object
	 */
	public MyLinkedList()
	{
	}
	
	public int size() {return size;}
	

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
 
	    Node p = headNode;
	    for(int i = 0; i < n; i++)
	    {
	    	p = p.nextNode;
	    	
	    }
	    return p.item;
	    
	}
	

	/**
	 * Set item/data v at index n.
	 * @param n index
	 * @param v value
	 */
	public void set(int n, K v)
	{
	    if(n < 0 || n >= size)
	    {
	        System.err.println("\n Illegal Access. Program Terminates...");
	        System.exit(1);
	    }
	    Node p = headNode;
	    for (int i = 0; i < n; i++)
	    {
	    	p = p.nextNode;
	    }
	    p.item = v;
	}
	
	

	/**
	 * Adds one item to the end of the list.
	 * @param item data to add to end of the list
	 */
	public void pushBack(K item)
	{
		Node newNode = new Node();
		newNode.item = item;

		if(headNode == null)
		{
			newNode.nextNode = headNode;
			headNode = newNode;

		}
		else
		{
			Node p = headNode;
			while (p.nextNode != null) 
				p = p.nextNode;
			
			p.nextNode = newNode;
			newNode.nextNode = null;

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
	}
	

	/**
	 * Prints the items (in order) in the list
	 * @param <K> Stored Object type
	 * @param list list to print to System.out
	 */
	public static <K> void print(MyLinkedList<K> list)
	{
	    for(int i = 0; i < list.size(); i++)
	        System.out.print(list.get(i) + "  ");
	}
}
