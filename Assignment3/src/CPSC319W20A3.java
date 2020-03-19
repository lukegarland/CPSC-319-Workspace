import java.io.IOException;
import java.nio.file.Files;
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
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.print("Enter the input filename: ");
		Scanner s = new Scanner(System.in);

		String filename = s.nextLine();
		System.out.println();
		
		String[] words;
		try 
		{
			words = importFromFile(filename);
		} catch (Exception e) 
		{
			e.printStackTrace();
			s.close();
			return;
		}
		BinaryWordTree tree = new BinaryWordTree();
		
		for(int i = 0; i < words.length; i++)
		{
			Word word = new Word(words[i]);
			tree.insert(word);
		}
		
		
		System.out.printf("Total words in %s = %d\n", filename, tree.getSize());
		System.out.printf("Number of unique words in %s = %d\n", filename, tree.getUniqueSize());
		System.out.printf("The word(s) which occur(s) most often and the number of times they occur(s) = \n");
		tree.printMostUsed();
		System.out.println();
		System.out.printf("Maximum height of the tree = %d\n\n", tree.maximumDepth());
		
		while(true)
		{
			System.out.printf("Enter s to search, t to traverse, and q to quit: ");
			String input = s.nextLine();
			if(input.equalsIgnoreCase("s"))
				search(tree, filename);
			else if(input.equalsIgnoreCase("t"))
				traverseTree(tree, filename);
			else if (input.equalsIgnoreCase("q"))
				break;
		}
		
	}

	public static String[] importFromFile(String filename) throws IOException
	{
		
		String lines;
		String[] words;

		lines = new String(Files.readAllBytes(Paths.get(filename)));
		words = lines.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
		return words;
	}

	public static void traverseTree(BinaryWordTree tree, String filename)
	{

		System.out.printf("Enter the BST traversal method (%d = IN-ORDER, %d = PRE-ORDER, %d = POST-ORDER for %s? ",
						  TraversalMethods.IN_ORDER.ordinal()+1, TraversalMethods.PRE_ORDER.ordinal()+1, TraversalMethods.POST_ORDER.ordinal()+1, filename);
		
		Scanner s = new Scanner(System.in);
		int input = s.nextInt();
		s.nextLine();
		
		s.close();
		tree.print(TraversalMethods.values()[input-1]);
	}
	
	public static void search(BinaryWordTree tree, String filename)
	{
		System.out.printf("Enter the word you are looking for in %s ? ", filename);
		Scanner s = new Scanner(System.in);
		Word query = new Word(s.nextLine());
		s.close();
		tree.search(query);
	}
}
