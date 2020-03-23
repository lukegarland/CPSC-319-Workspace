import java.util.ArrayList;


/**
 * 			General binary search tree with basic insert, print/traverse, and search functionality.
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class BinaryTree <T extends Comparable<T>>{

	
	protected class Node
	{
		Node lessNode; // left node
		Node greaterNode; // right node
		T object; // object
		int frequency; // number of times the object is 'inserted'
		
		public Node(T obj)
		{
			lessNode 	= null;
			greaterNode = null;
			object 		= obj;
			frequency	= 1;
		}
		public void increment() {frequency++;};
		
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
	 * If the object is already in the tree, then its node's frequency will be incremented
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
		
		while(true) // Avoid recursion, because there is no need to go back up the tree once item is placed.
		{
			if(currentNode.object.compareTo(nodeToAdd.object) == 0)
			{
				currentNode.increment();
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
	 * 
	 */
	public void print(TraversalMethods method)
	{
		if(size == 0)
		{
			System.out.println("Tree is empty");
			System.out.println();

			return;
		}
		
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
	 * Search for an object and print the result.
	 * If word is found, method will print the object's frequency. If the object is not found, it will notify the user accordingly.
	 * @param query object to search
	 */
	public void search(T query)
	{
		Node word = binarySearch(root, query);
		if(word == null)
			System.err.println("Word not found!\n");
		else
			System.out.printf("Found! It appears %d times in the input text file\n\n", word.frequency);

	}

	

	/**
	 * Helper function for traversal when calling search(T query).
	 * Uses pre-order traversal.
	 * @param node
	 * @param query
	 * @return
	 */
	protected Node binarySearch(Node node, T query)
	{

		if(node == null) // if node/tree is empty
			return null;
		
		Node rv = null; // return value placeholder, assume null in case recursive calls do not
		
		if(node.object.compareTo(query) == 0)
			rv = node; // word is found, return the object in the node
		
		if(query.compareTo(node.object) < 0) // if word is less than word in the current node, then go left if it exists
			if(node.lessNode != null)
				rv = binarySearch(node.lessNode, query);
		
		
		if(query.compareTo(node.object) > 0) // if word is greater than word in the current node, then go right if it exists
			if(node.greaterNode != null)
			rv = binarySearch(node.greaterNode, query);
		
		
		return rv; 	// rv == null if recursive calls do not find the word
					// returns the word object in the tree if found
	}
	
	/**
	 * @return number of unique objects (frequency == 1) in the tree
	 */
	public int getUniqueSize()
	{
		return uniqueSizeTraverse(root, 0);
	}
	

	/**
	 * Helper method for traversing tree when calling getUniqueSize()
	 * Uses pre-order traversal.
	 * @param node current node in the traversal
	 * @param uniqueSize current unique size
	 * @return updated unique size after checking the current node, and it's children
	 */
	private int uniqueSizeTraverse(Node node, int uniqueSize)
	{
		
		if (node.frequency == 1)
			uniqueSize++;
		
		if(node.lessNode != null)	
			uniqueSize = uniqueSizeTraverse(node.lessNode, uniqueSize);

		
		if(node.greaterNode != null)	
			uniqueSize = uniqueSizeTraverse(node.greaterNode, uniqueSize);
		
		return uniqueSize;
	}


	

	
	/**
	 * Prints the objects in the tree that appear most often, and their frequency.
	 */
	public void printMostUsed()
	{
		ArrayList<Node> mostUsed = new ArrayList<Node>();
		if(size == 0)
			return;
		
		mostUsed.add(root);
		
		if(root.lessNode != null)	
			mostUsedTraverse(root.lessNode, mostUsed);


		if(root.greaterNode != null)	
			mostUsedTraverse(root.greaterNode, mostUsed);

		for(Node n: mostUsed)
		{
			System.out.printf("%s = %d times\n", n.object.toString(), n.frequency);
		}
	}
	
	
	/**
	 * Helper function for traversing tree when calling printMostUsed()
	 * Uses pre-order traversal.
	 * @param node current node in the traversal
	 * @param mostUsed current word(s) that have highest frequency
	 */
	private void mostUsedTraverse(Node node, ArrayList<Node> mostUsed)
	{
		
		if(mostUsed.get(0).frequency < node.frequency)
		{
			mostUsed.clear();
			mostUsed.add(node);
		}
		
		else if(mostUsed.get(0).frequency == node.frequency)
				mostUsed.add(node);
		
		if(node.lessNode != null)	
			mostUsedTraverse(node.lessNode, mostUsed);

		if(node.greaterNode != null)	
			mostUsedTraverse(node.greaterNode, mostUsed);
		
	}


	
}
