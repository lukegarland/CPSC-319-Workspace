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
	 * Global variables to avoid passing the filename and scanner to function calls
	 */
	static String filename;
	static Scanner scan = new Scanner(System.in);

	/**
	 * Main program
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		
		
		String[] words = importFromFile();

		BinaryWordTree tree = new BinaryWordTree();

		for(int i = 0; i < words.length; i++)
		{
			Word word = new Word(words[i]); // Fill tree with words
			tree.insert(word);
		}

		printTreeData(tree);

		String input;

		System.out.print("Enter s to search, t to traverse, and q to quit: ");

		while(scan.hasNextLine())
		{

			input = scan.nextLine();

			if(input.equalsIgnoreCase("s"))
				search(tree);
			else if(input.equalsIgnoreCase("t"))
				traverseTree(tree);
			else if (input.equalsIgnoreCase("q"))
				break;
			System.out.print("Enter s to search, t to traverse, and q to quit: ");

		}
		scan.close();

	}

	
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
				System.out.print("Enter the input filename: ");
				filename = scan.nextLine();
				System.out.println();			
				String lines;
				lines = new String(Files.readAllBytes(Paths.get(filename))); // Read everything in the file.
				words = lines.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+"); //Replaced all non letter characters with a space, then split by whitespace.
				return words;
			}		
			catch (NoSuchFileException e)
			{
				System.err.println("\nFile not found. Please Try again\n");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.err.println("\nPlease Try again\n");
			}
			
		}
	}

	/**
	 * Print's the output of a traversal of the tree.
	 * This method will ask the user which BST traversal method they would like. Options are pre-order, in-order, or post-order.
	 * @param tree Tree to traverse
	 */
	public static void traverseTree(BinaryWordTree tree)
	{ 
		int input;

		System.out.printf("Enter the BST traversal method (%d = IN-ORDER, %d = PRE-ORDER, %d = POST-ORDER for %s? ",
		  		  TraversalMethods.IN_ORDER.ordinal()+1, TraversalMethods.PRE_ORDER.ordinal()+1, TraversalMethods.POST_ORDER.ordinal()+1, filename);
		
		while(scan.hasNextLine())
			try 
			{ 	
				input = Integer.parseInt(scan.nextLine());
				tree.print(TraversalMethods.values()[input-1]);
				return;	
			}catch(NumberFormatException e)
			{
				System.err.println("Input Error. Try again\n");
			}				
	}
	
	/**
	 * Search for a word in a tree and print the result.
	 * @param tree Tree to search
	 */
	public static void search(BinaryWordTree tree)
	{
		System.out.printf("Enter the word you are looking for in %s ? ", filename);
		Word query = new Word(scan.nextLine());
		tree.search(query);
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
	public static void printTreeData(BinaryWordTree tree)
	{
		System.out.printf("Total words in %s = %d\n\n", filename, tree.getSize());
		System.out.printf("Number of unique words in %s = %d\n\n", filename, tree.getUniqueSize());
		System.out.printf("The word(s) which occur(s) most often and the number of times they occur(s) = \n");
		tree.printMostUsed();
		System.out.println();
		System.out.printf("Maximum height of the tree = %d\n\n", tree.maximumDepth());
		
	}
}
