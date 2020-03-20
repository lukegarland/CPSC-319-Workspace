/**
 * 			General binary search tree with basic insert, print/traverse, and search functionality.
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class BinaryTree <T extends Comparable<T>>{

	
	protected class Node
	{
		Node lessNode; //left node
		Node greaterNode; //right node
		T object; //object
		
		public Node()
		{
			lessNode 	= null;
			greaterNode = null;
			object 		= null;
		}
		
		public Node(T obj)
		{
			lessNode 	= null;
			greaterNode = null;
			object 		= obj;
		}
		
		public boolean isLeaf()
		{
			return (this.lessNode == null && this.greaterNode == null);
		}
	}
	
	
	protected Node root;
	protected int size;
	
	
	/**
	 * Constructor to create empty binary tree
	 */
	public BinaryTree()
	{
		root = null;
		size = 0;
	}

	/**
	 * Constructor to create binary tree with initial element obj
	 */
	public BinaryTree(T obj)
	{
		root = new Node(obj);
		size = 1;
	}
	
	
	/**
	 * @return the number of nodes in the tree
	 */
	public int getSize() 
	{
		return size;
	}
	
	
	/**
	 * Insert object into tree. 
	 * Based on the natural (obj.compareTo(o)) ordering of the object. 
	 * @param obj Object to add
	 */
	public void insert(T obj)
	{
		Node nodeToAdd = new Node(obj);
		
		if(size == 0) // Empty tree case.
		{
			root = nodeToAdd;
			size++;
			return;
		}
		
		Node currentNode = root;
		
		while(true) // Avoid recursion. There is no need to go back up the tree once item is placed.
		{
			if(currentNode.object.compareTo(nodeToAdd.object) == 0)
			{

				return;
			}
			
			if(currentNode.object.compareTo(nodeToAdd.object) > 0)
			//if nodeToAdd is less than currentNode, go left
			{
				// Check if left is empty
				if(currentNode.lessNode == null)
				{
					currentNode.lessNode = nodeToAdd;
					size++;
					return;
				}
				else
				{
					currentNode = currentNode.lessNode;
					continue;
				}

			}

			else if (currentNode.object.compareTo(nodeToAdd.object) < 0)
			//if nodeToAdd is greater than currentNode, go right
			{
				if(currentNode.greaterNode == null)
				{
					currentNode.greaterNode = nodeToAdd;
					size++;
					return;
				}
				else 
				{
					currentNode = currentNode.greaterNode;
					continue;					
				}
	
			}
		}// end while(true)
	}
	

	

	
	
	
	/**
	 * Prints a traversal of the tree using specified method
	 * @param method Traversal method (in-order, pre-order, or post-order)
	 */
	public void print(TraversalMethods method)
	{
		
		switch(method)
		{
			case IN_ORDER:
				System.out.print("IN-ORDER output: ");
				printInOrder(root);
				break;

			case PRE_ORDER:
				System.out.print("PRE-ORDER output: ");
				printPreOrder(root);
				break;
				
			case POST_ORDER:
				System.out.print("POST-ORDER output: ");
				printPostOrder(root);
				break;
		}
		System.out.println();
		
	}
	
	/**
	 * Helper method for traversing tree when calling print().
	 * Uses in-order traversal.
	 * @param node current node in the traversal
	 */
	private void printInOrder(Node node) 
	{
		if(node.isLeaf())
		{	
			System.out.print(node.object.toString()+ " ");
			return;
		}
		
		if(node.lessNode != null)	
			printInOrder(node.lessNode);
		
		System.out.print((node.object.toString() + " "));
		if(node.greaterNode != null)	
			printInOrder(node.greaterNode);
	}

	/**
	 * Helper method for traversing tree when calling print().
	 * Uses pre-order traversal.
	 * @param node current node in the traversal
	 */
	private void printPreOrder(Node node) 
	{
		if(node.isLeaf())
		{	
			System.out.print(node.object.toString()+ " ");
			return;
		}

		
		System.out.print((node.object.toString() + " "));
		
		if(node.lessNode != null)	
			printPreOrder(node.lessNode);
		
		if(node.greaterNode != null)	
			printPreOrder(node.greaterNode);
	}
	
	/**
	 * Helper method for traversing tree when calling print().
	 * Uses post-order traversal.
	 * @param node current node in the traversal
	 */
	private void printPostOrder(Node node)
	{
		if(node.isLeaf())
		{	
			System.out.print(node.object.toString()+ " ");
			return;
		}
		if(node.lessNode != null)	
			printPostOrder(node.lessNode);
		
		if(node.greaterNode != null)	
			printPostOrder(node.greaterNode);
		
		System.out.print((node.object.toString() + " "));

	}

	/**
	 * @return maximum depth of the tree
	 */
	public int maximumDepth() {return getDepth(root);}
	
	/**
	 * Helper method for traversing tree when calling maximumDepth().
	 * Uses pre-order traversal.
	 * @param node current node in the traversal
	 * @return current max depth of the tree
	 */
	private int getDepth(Node node)
	{
		if(node == null)
			return 0;
		
		int lessDepth = getDepth(node.lessNode);
		int greaterDepth = getDepth(node.greaterNode);
		
		if (lessDepth < greaterDepth)
			return ++greaterDepth;
		else 
			return ++lessDepth;
		
	}
	
	/**
	 * Search for object in tree.
	 * 
	 * @param query object to search for
	 * @return requested object, null if object is not found
	 */
	public T binarySearch(T query)
	{
		return binarySearch(root, query);
	}
	
	/**
	 * Helper function for traversal when calling binarySearch(T query).
	 * Uses pre-order traversal.
	 * @param node
	 * @param query
	 * @return
	 */
	protected T binarySearch(Node node, T query)
	{

		if(node == null) // if node/tree is empty
			return null;
		
		T rv = null; // return value placeholder, assume null in case recursive calls do not
		
		if(node.object.compareTo(query) == 0)
			rv = node.object; // word is found, return the object in the node
		
		if(query.compareTo(node.object) < 0) // if word is less than word in the current node, then go left if it exists
			if(node.lessNode != null)
				rv = binarySearch(node.lessNode, query);
		
		
		if(query.compareTo(node.object) > 0) // if word is greater than word in the current node, then go right if it exists
			if(node.greaterNode != null)
			rv = binarySearch(node.greaterNode, query);
		
		
		return rv; 	// rv == null if recursive calls do not find the word
					// returns the word object in the tree if found
	}
	

	
	
}
