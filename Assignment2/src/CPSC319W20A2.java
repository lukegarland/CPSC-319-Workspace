import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 			CPSC319W20A2.java
 * 			@author lukeg
 * 			@since	Feb. 21, 2020
 * 
 */

public class CPSC319W20A2 {

	
	public static Comparator<String> compareString = new Comparator<String>() {
	    @Override
	    public int compare(String e1, String e2) {
		    return e1.compareToIgnoreCase(e2);
	    }
    };
    
	public static Comparator<Character> compareChar = new Comparator<Character>() {
	    @Override
	    public int compare(Character e1, Character e2) {
	    	e1 = Character.toLowerCase(e1);
		    return e1.compareTo(Character.toLowerCase(e2));
	    }
    };
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		assert(args.length > 0);
		
		String filename = args[0]; // First argument is file name
		
		String fileContent;
		try 
		{
			fileContent = new String ( Files.readAllBytes(Paths.get(filename))); // open file and read all content
			
		} catch (IOException e) 
		{
			System.err.printf("Unable to read the content from %s\n\n", filename);
			e.printStackTrace();
			return;
		}
		
		String[] listA = fileContent.split("\\s+"); // Split by whitespace
		
		mergeSort(listA, compareString); // Sort words
		
		
		ArrayList <MyLinkedList<String>> listB = new ArrayList<MyLinkedList<String>>(listA.length);
		
		
		for(int i_a = 0; i_a < listA.length; i_a++)
		{
			String word = listA[i_a];
			if(word == null)
				continue;
			
			MyLinkedList<String> linkedListToAdd = new MyLinkedList<String>();
			linkedListToAdd.pushBack(word);
			
			
			for(int j = i_a +1 ; j < listA.length; j++)
			{
				if(listA[j] == null)
					continue;
				
				if(isAnagram(word, listA[j]))
				{
					linkedListToAdd.pushBack(listA[j]);
					listA[j] = null;
				}
					
			}
			listB.add(linkedListToAdd);
			
		}
		
		for(int i = 0; i<listB.size(); i++)
		{
			MyLinkedList.print(listB.get(i));
			System.out.println();
		}
			
		


	}
	
	
	
	
	
	
	
	public static <K> void mergeSort(K[] S, Comparator<K> comp)
	{
		int n = S.length;
		if(n<2)		// trivial case
			return;
		// divide
		
		int mid = n/2;
		
		K[] S1 = Arrays.copyOfRange(S, 0, mid);
		K[] S2 = Arrays.copyOfRange(S, mid, n);
		
		mergeSort(S1, comp);
		mergeSort(S2, comp);
		
		merge(S1, S2, S, comp);
		
	}


	
	private static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp ) 
	{
		int i = 0, j = 0;
		while (i+j < S.length)
		{
			if( j == S2.length || (i<S1.length && (comp.compare(S1[i], S2[j])) < 0))
				S[i+j] = S1[i++];
			else
				S[i+j] = S2[j++];
		}
		
	}
	
	
	public static boolean isAnagram(String a, String b)
	{

		Character[] aSorted = charArrayToCharacterArray(a.toCharArray());

				
		Character[] bSorted = charArrayToCharacterArray(b.toCharArray());
		
		mergeSort(aSorted, compareChar);
		mergeSort(bSorted, compareChar);

		if(characterArrayToString(aSorted).equalsIgnoreCase(characterArrayToString(bSorted)))
			return true;
		else
			return false;
		
	}

	
	private static Character[] charArrayToCharacterArray(char [] c)
	{
		Character[] characterArray = new Character[c.length];
		
		for (int i = 0; i < c.length; i++)
		{
			characterArray[i] = c[i];
		}
		
		return characterArray;
		
	}
	
	private static String characterArrayToString(Character[] c)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < c.length; i++)
		{
			sb.append(c[i]);
		}
		
		return sb.toString();
		
	}
}
