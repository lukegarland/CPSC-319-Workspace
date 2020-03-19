import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * 			BinaryTree.java
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class BinaryWordTree{
	
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
		
		public boolean isLeaf()
		{
			return (this.lessNode == null && this.greaterNode == null);
		}
	}
	
	
	private Node root;
	private int size;

	
	
//	======== Constructors ========
	
	
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
	
	
//	==============================

	
	/**
	 * @return the number of nodes in the tree
	 */
	public int getSize() 
	{
		return size;
	}

	
	
	public void insert(Word obj)
	{
		Node nodeToAdd = new Node(obj);
		
		if(size == 0)
		{
			root = nodeToAdd;
			size++;
			return;
		}
		
		Node currentNode = root;
		
		while(true)
		{
			if(currentNode.object.compareTo(nodeToAdd.object) == 0)
			{
				currentNode.object.increment();
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
					break;
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
					break;
				}
				else 
				{
					currentNode = currentNode.greaterNode;
					continue;					
				}
	
			}
		}
		
		
	}
	

	

	
	
	
	
	public void print(TraversalMethods method)
	{
		//StringBuilder sb = new StringBuilder();
		
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
	 * @param node
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
	 * @param root
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
	 * @param node
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
	
	
	
	
	
	
	
	
	
	
	private int uniqueSizeTraverse(Node node, int uniqueSize)
	{
		if(node.isLeaf())
		{	
			if (node.object.getFrequency() == 1)
				uniqueSize++;
			return uniqueSize;
		}
		
		
		if (node.object.getFrequency() == 1)
			uniqueSize++;
		
		if(node.lessNode != null)	
			uniqueSize = uniqueSizeTraverse(node.lessNode, uniqueSize);

		
		if(node.greaterNode != null)	
			uniqueSize = uniqueSizeTraverse(node.greaterNode, uniqueSize);
		
		return uniqueSize;
	}

	public int getUniqueSize()
	{
		return uniqueSizeTraverse(root, 0);
	}
	
	
	
	
	
	
	
	
	public int maximumDepth() {return getDepth(root);}
	
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
	
	
	
	
	
	
	
	
	
	public void printMostUsed()
	{
		ArrayList<Word> mostUsed = new ArrayList<Word>();
		if(size == 0)
			return;
		mostUsed.add(root.object);
		
		if(root.lessNode != null)	
			mostUsedTraverse(root.lessNode, mostUsed);


		if(root.greaterNode != null)	
			mostUsedTraverse(root.greaterNode, mostUsed);

		for(Word w: mostUsed)
		{
			System.out.printf("%s = %d times\n",w.toString(), w.getFrequency());
		}
	}
	
	
	private void mostUsedTraverse(Node node, ArrayList<Word> mostUsed)
	{
		
		if(mostUsed.get(0).getFrequency() < node.object.getFrequency())
		{
			mostUsed.clear();
			mostUsed.add(node.object);
		}
		
		else if(mostUsed.get(0).getFrequency() == node.object.getFrequency())
				mostUsed.add(node.object);
		
		if(node.lessNode != null)	
			mostUsedTraverse(node.lessNode, mostUsed);

		if(node.greaterNode != null)	
			mostUsedTraverse(node.greaterNode, mostUsed);
		
	}
	
	
	
	

	public void search(Word query)
	{
		Word word = searchTraverse(root, query);
		if(word == null)
			System.err.println("Word not found!\n");
		else
			System.out.printf("Found! It appears %d times in the input text file\n\n", word.getFrequency());

	}

	private Word searchTraverse(Node node, Word word)
	{

		Word rv = null; // return value placeholder, assume null in case recursive calls do not
		
		if(node.object.compareTo(word) == 0)
			rv = node.object; // word is found, return the object in the node
		
		if(word.compareTo(node.object) < 0) // if word is less than word in the current node, then go left if it exists
			if(node.lessNode != null)
				rv = searchTraverse(node.lessNode, word);
		
		
		if(word.compareTo(node.object) > 0) // if word is greater than word in the current node, then go right if it exists
			if(node.greaterNode != null)
			rv = searchTraverse(node.greaterNode, word);
		
		
		return rv; 	// rv == null if recursive calls do not find the word
					// returns the word object in the tree if found
	}
	
}
