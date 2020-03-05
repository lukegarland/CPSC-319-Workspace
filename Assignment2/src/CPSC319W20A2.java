import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 			CPSC319 Assignment 2.
 * 			Reads a file of whitespace separated words and prints a list of all the words with their anagrams.
 * 			@author lukeg
 * 			@since	Feb. 21, 2020
 * 
 */

public class CPSC319W20A2 {
	
	
	/**
	 * Gets input stream System.in until a blank line is entered.
	 * @return String of the input from System.in
	 */
	private static String getInput()
	{
		StringBuilder sb = new StringBuilder();
		Scanner s = new Scanner(System.in);
		String userInput;
		
		while(s.hasNextLine())
		{
			userInput = s.nextLine();
			
			if(userInput.equals(""))
				break;
			
			sb.append(userInput);
			sb.append("\n");//replace \n consumed by s.nextLine()
		}
		s.close();
		
		return sb.toString();
	}
	
	
	/**
	 * Sorts the characters in a String. 
	 * Sorting algorithm used is insertion sort.
	 * @param word String to sort
	 * @return sorted characters in {@code word}
	 */
	private static String sortWordCharacters(String word) 
	{
		char [] wordChars = word.toCharArray();
		insertionSort(wordChars);
		return String.copyValueOf(wordChars);
	}
	
	/**
	 * Insertion sort algorithm for characters.
	 * Used to sort the individual characters in a word, based on ASCII ordering.
	 * @param a Array of chars to sort.
	 */
	private static void insertionSort(char[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			char t = a[i];
			
			int j;
			
			for(j = i-1; j>= 0 && a[j] > t; j--) // break if at end of array, or if current element is greater
												 // than t
				a[j+1] = a[j]; // Move elements greater than t one position ahead
			
			a[j+1] = t; // Swap t with the element that is greater than t
		}
	}
	
	

	/**
	 * Merge sort algorithm for Strings.
	 * Used to sort an array of strings. Strings are sorted in lexicographical (letter case is ignored).
	 * Source: Data Structure & Algorithms Sixth Edition by M. T. Goodrich, R. Tamassia, M. H. Goldwater. Page 537-538. 
	 * Adaptations by: Luke Garland
	 * 
	 * @param S
	 */
	private static void mergeSort(String[] S)
	{
		int n = S.length;
		if(n<2)		// trivial case
			return;
		// divide
		
		int mid = n/2;
		
		String[] S1 = Arrays.copyOfRange(S, 0, mid);
		String[] S2 = Arrays.copyOfRange(S, mid, n);
		
		mergeSort(S1);
		mergeSort(S2);
		
		merge(S1, S2, S);
		
	}


	
	/**
	 * Helper function for {@code mergeSort}.
	 * Merges S1 and S2 into S based on lexicographical ordering of each string. 
	 * Source: Data Structure & Algorithms Sixth Edition by M. T. Goodrich, R. Tamassia, M. H. Goldwater. Page 537-538. 
	 * Adaptations by: Luke Garland
	 * @param S1 String 1
	 * @param S2 String 2
	 * @param S String to be overwritten
	 */
	private static void merge(String[] S1, String[] S2, String[] S) 
	{
		int i = 0, j = 0;
		while (i+j < S.length)
		{
			if( j == S2.length || (i<S1.length && (S1[i].compareTo((S2[j])) < 0)))
				S[i+j] = S1[i++];
			else
				S[i+j] = S2[j++];
		}
		
	}
	

	
	/**
	 * Main program.
	 * Lists anagrams of all words in a file.
	 * @param args First command line argument must be a path to a readable file
	 */
	public static void main(String[] args)
	{
		String[] listA = getInput().split("\\s+"); // Split user input by whitespace
		
		mergeSort(listA); // Sort list of words
		
		/*
		 *  Create list B. ArrayList was used as Java does not support generic 
		 *  array types. 
		 * (e.g. MyLinkedList<String>[] listB = new MyLinkedList<String>[listA.length] will not compile)
		 */
		ArrayList <MyLinkedList<String>> listB = new ArrayList<MyLinkedList<String>>(listA.length); 

		String[] anagramCheckArray = Arrays.copyOf(listA, listA.length);
		

		for(int i = 0; i < anagramCheckArray.length; i++)
		{
			anagramCheckArray[i] = sortWordCharacters(anagramCheckArray[i]);
			// Sort the characters in each word, and store in anagramCheckArray so each word only needs to be sorted once.
		}
		
		
		for(int i = 0; i < listA.length; i++)
		{
			String word = listA[i]; // Word to check
			
			if(word == null) // If current word is already an anagram, skip.
				continue;
			
			MyLinkedList<String> linkedListToAdd = new MyLinkedList<String>(word); // Create new linked list of Strings, put word at the start
			
			String sortedWord = anagramCheckArray[i];
			
			for(int j = i +1 ; j < listA.length; j++) // Check for anagrams

			{
				if(listA[j] == null) //Skip if entry has already been checked
					continue;

				if(sortedWord.equals(anagramCheckArray[j])) // Check if sorted characters are the same
				{
					linkedListToAdd.pushBack(listA[j]); // Add entry to linked list, as they are an anagram
					listA[j] = null; // Remove entry from listA
				}
			}
			
			listB.add(linkedListToAdd); // Add linked list of anagrams to list B
		}
		
		// Print all contents of listB
		for(MyLinkedList<String> list: listB)
		{
			System.out.println(list);
		}
		


	}
	
	
}
