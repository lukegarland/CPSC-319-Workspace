import java.util.ArrayList;



/**
 * 			Binary search tree for Word types, with specific functionality for type Word. 
 * 			Used to keep track of words, and the frequency of which each word occurs.
 * 			See also: BinaryTree.java
 * 			@author lukeg
 * 			@since	Mar. 19, 2020
 * 
 */

public class BinaryWordTree extends BinaryTree<Word>{

	/**
	 * Constructor to create empty binary tree
	 */
	public BinaryWordTree()
	{
		super();
	}
	
	/**
	 * Constructor to create binary tree with initial word
	 */
	public BinaryWordTree(Word obj)
	{
		super(obj);
	}

	/**
	 * Insert word into tree.
	 * Overrides BinaryTree's insert(T obj), because if a word is already in the tree, the word's frequency must be incremented.
	 */
	@Override
	public void insert(Word obj)
	{
		Word result = super.binarySearch(root, obj);
		
		if(result != null)
			result.increment(); // Increment frequency if word is already in the tree.
		else
			super.insert(obj);
	}
	
	/**
	 * @return number of unique words (frequency == 1) in the tree
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


	

	
	/**
	 * Prints the word(s) that appear most often, and their frequency.
	 */
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
	
	
	/**
	 * Helper function for traversing tree when calling printMostUsed()
	 * Uses pre-order traversal.
	 * @param node current node in the traversal
	 * @param mostUsed current word(s) that have highest frequency
	 */
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

	
	/**
	 * Search for a word and print the result.
	 * If word is found, method will print the word's frequency. If the word is not found, it will notify the user accordingly.
	 * @param query word to search
	 */
	public void search(Word query)
	{
		Word word = binarySearch(root, query);
		if(word == null)
			System.err.println("Word not found!\n");
		else
			System.out.printf("Found! It appears %d times in the input text file\n\n", word.getFrequency());

	}

}
