import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *			Main runnable class for CPSC 319 Assignment 3 Winter 2020
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class CPSC319W20A3{

	
	/**
	 * Global variables to avoid passing the filename and scanner to function calls inside this class
	 */
	static String filename;
	static Scanner scan = new Scanner(System.in);

	
	/**
	 * Gets all words from a text file.
	 * Imports a text file of non-letter separated words. The user will be requested to specify the text file from System.in.
	 * This method will block until the user enters a path to a readable file.
	 * @return An array of words from the text file.
	 */
	public static String[] importFromFile()
	{
		String[] words;
		while(true) // Continuously ask for input file if file cannot be opened 
		{

			try 
			{
				System.out.println("Enter the input filename:");
				filename = scan.nextLine();

				String lines;
				lines = new String(Files.readAllBytes(Paths.get(filename))); // Read everything in the file.
				words = lines.replaceAll("[^0-9a-zA-Z]", " ").toLowerCase().trim().split("\\s+"); //Replaced all non letter characters with a space, then split by whitespace.
				
				// trim() was added to remove leading and trailing whitespace. For example,
				// if input was "\n a b c \n d\n e" words would be ["", "a", "b", "c", "d", "e"]

				return words;
			}		
			catch (NoSuchFileException e)
			{
				System.out.println("\nFile not found. Please Try again\n");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("\nPlease Try again\n");
			}
			
		}
	}

	/**
	 * Print's the output of a traversal of the tree.
	 * This method will ask the user which BST traversal method they would like. Options are pre-order, in-order, or post-order.
	 * @param tree Tree to traverse
	 */
	public static void traverseTree(BinaryTree<String> tree)
	{ 
		String input;
		int method;

		while(true)
			try
			{ 	
				System.out.printf("Enter the BST traversal method (%d = IN-ORDER, %d = PRE-ORDER, %d = POST-ORDER for %s?\n",
									TraversalMethods.IN_ORDER.ordinal()+1, TraversalMethods.PRE_ORDER.ordinal()+1, TraversalMethods.POST_ORDER.ordinal()+1, filename);
				
				input = scan.nextLine();
				
				if(input.compareTo("") == 0)
					return;
				
				method = Integer.parseInt(input);
				tree.print(TraversalMethods.values()[method-1]);
			}catch(NumberFormatException e)
			{
				System.out.println("Input Error. Try again\n");
			}				
	}
	
	/**
	 * Search for a word in a tree and print the result.
	 * @param tree Tree to search
	 */
	public static void search(BinaryTree<String> tree)
	{
		while(true)
		{
			System.out.printf("Enter the word you are looking for in %s ?\n", filename);
			String query = scan.nextLine();
			if(query.compareTo("") == 0)
			{
				return;
			}
			tree.search(query);
		}

	}
	
	/**
	 * Prints data about the tree.
	 * The following data is printed to System.out
	 * - Total number of words (nodes) in the tree.
	 * - Total number of unique (frequency = 1) words in the tree.
	 * - The word(s) that appear most often, and their frequency.
	 * - Tree depth.
	 * @param tree 
	 */
	public static void printTreeData(BinaryTree<String> tree)
	{
		System.out.printf("Total words in %s = %d\n", filename, tree.getSize());
		System.out.printf("Number of unique words in %s = %d\n", filename, tree.getUniqueSize());
		System.out.printf("The word(s) which occur(s) most often and the number of times they occur(s) = \n");
		tree.printMostUsed();
		System.out.printf("Maximum height of the tree = %d\n", tree.maximumDepth());
		
	}
	
	

	/**
	 * Main program
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		
		
		String[] words = importFromFile();
		BinaryTree<String> tree = new BinaryTree<String>();

		for(int i = 0; i < words.length; i++)
		{
			// Fill tree with words
			tree.insert(words[i]);
		}

		printTreeData(tree);

		search(tree);
		traverseTree(tree);
	
		scan.close();

	}

	
}
