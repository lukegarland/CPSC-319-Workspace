
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 			CPSC319W20A3.java
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
	 * @param args
	 */
	public static void main(String[] args)
	{

		String[] words = importFromFile();

		BinaryWordTree tree = new BinaryWordTree();

		for(int i = 0; i < words.length; i++)
		{
			Word word = new Word(words[i]);
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

	
	public static String[] importFromFile()
	{
		String[] words;
		while(true)
		{

			try 
			{
				System.out.print("Enter the input filename: ");
				filename = scan.nextLine();
				System.out.println();			
				String lines;
				lines = new String(Files.readAllBytes(Paths.get(filename)));
				words = lines.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
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
				break;	
			}catch(NumberFormatException e)
			{
				System.err.println("Input Error. Try again\n");
			}				
	}
	
	public static void search(BinaryWordTree tree)
	{
		System.out.printf("Enter the word you are looking for in %s ? ", filename);
		Word query = new Word(scan.nextLine());
		tree.search(query);
	}
	
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
