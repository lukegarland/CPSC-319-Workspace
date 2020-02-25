import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	 * Main program.
	 * Lists anagrams of all words in a file.
	 * @param args First command line argument must be a path to a readable file
	 */
	public static void main(String[] args)
	{

		if(args.length < 1)
		{
			System.err.println("No File found"); // Ensure there is at least one command line argument
			return;
		}
			
		
		String filename = args[0]; // First argument is file name
		
		String fileContent;
		
		try 
		{
			fileContent = new String(Files.readAllBytes(Paths.get(filename))); // Open file and read all content
			
		} catch (IOException e) 
		{
			System.err.printf("Unable to read the content from %s\n\n", filename);
			e.printStackTrace();
			return;
		}


		String[] listA = fileContent.split("\\s+"); // Split file content by whitespace
		
		mergeSort(listA); // Sort list of words
		
		
		
		/*
		 *  Create list B. ArrayList was used as Java does not support generic 
		 *  array types. 
		 * (e.g. MyLinkedList<String>[] listB = new MyLinkedList<String>[listA.length] will not compile)
		*/
		ArrayList <MyLinkedList<String>> listB = new ArrayList<MyLinkedList<String>>(listA.length); 


		for(int i = 0; i < listA.length; i++)
		{
			String word = listA[i]; // Word to check
			
			
			if(word == null) // If current word is already an anagram, skip.
				continue;
			
			MyLinkedList<String> linkedListToAdd = new MyLinkedList<String>(); // Create new linked list of Strings
			linkedListToAdd.pushFront(word);//Put word at the front
			
			// Check for anagrams
			for(int j = i +1 ; j < listA.length; j++)
			{
				if(listA[j] == null) //Skip if entry has already been checked
					continue;
				
				
				if(isAnagram(word, listA[j]))
				{
					linkedListToAdd.pushBack(listA[j]); // Add entry to linked list
					
					listA[j] = null; // Remove entry from listA
				}
					
			}
			
			listB.add(linkedListToAdd); // Add linked list of anagrams to list B
			
		}
		
		// Print all contents of listB
		for(int i = 0; i<listB.size(); i++)
		{
			MyLinkedList.print(listB.get(i));
			System.out.println();
		}
			
		


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
	 * @param S1 String 1
	 * @param S2 String 2
	 * @param S String to be overwritten
	 */
	private static  void merge(String[] S1, String[] S2, String[] S) 
	{
		int i = 0, j = 0;
		while (i+j < S.length)
		{
			if( j == S2.length || (i<S1.length && (S1[i].compareToIgnoreCase((S2[j])) < 0)))
				S[i+j] = S1[i++];
			else
				S[i+j] = S2[j++];
		}
		
	}
	
	
	/**
	 * Checks if two strings are anagrams of each other
	 * @param a String 1
	 * @param b String 2
	 * @return true if a is an anagram of b (and vice versa)
	 */
	private static boolean isAnagram(String a, String b)
	{

		// Convert to char array in order to sort.
		char[] aSorted = a.toCharArray();
		char[] bSorted =b.toCharArray();
		
		// Sort
		insertionSort(aSorted);
		insertionSort(bSorted);

		
		// Return true if aSorted is the same as bSorted
		if(String.copyValueOf(aSorted).equalsIgnoreCase(String.copyValueOf(bSorted)))
			return true;
		else
			return false;
		
	}
}
