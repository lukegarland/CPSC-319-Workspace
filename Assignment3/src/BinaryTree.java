/**
 * 			BinaryTree.java
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class BinaryWordTree implements TraversalMethods{
	
	@SuppressWarnings("unused")
	private class Node
	{
		Node lessNode; //left node
		Node greaterNode; //right node
		Word object; //object
		
		public Node()
		{
			lessNode 	= null;
			greaterNode = null;
			object 		= null;
		}
		
		public Node(Word obj)
		{
			lessNode 	= null;
			greaterNode = null;
			object 		= obj;
		}
		
	}
	
	
	private Node root;
	private int size;

	public BinaryWordTree()
	{
		root = null;
		size = 0;
	}

	public BinaryWordTree(Word obj)
	{
		root = new Node(obj);
		size = 1;
	}

	
	public void insert(Word obj)
	{
		
	}
	

	
	/**
	 * @return the size
	 */
	public int getSize() 
	{
		return size;
	}

	public Word search(Word query)
	{
		return null;
	}
	
	
	
	public String traverse(TraversalMethods method)
	{
		StringBuilder sb = new StringBuilder();
		
		
		return sb.toString();
		
	}
	
	public int getUniqueSize()
	{
		return 0;
	}
	
	public void printMostUsed()
	{
		
	}
	
	
}
