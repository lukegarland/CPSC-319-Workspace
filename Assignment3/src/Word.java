/**
 * 			Class for storing a single word and the frequency of how often it occurs.
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class Word implements Comparable<Word>{

	
	private String word;
	private int frequency;
	
	
	/**
	 * Constructor to create a word from a string.
	 * It is assumed that the word occurs once if it is constructed (i.e. frequency = 1). 
	 * @param word word to store
	 */
	public Word(String word)
	{
		this.word = word;
		this.frequency = 1;
	}
	
	/**
	 * @return Number of times the word occurs
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Increments the frequency by 1
	 */
	public void increment()
	{
		this.frequency++;
	}
	
	/**
	 * Uses String.compareTo() to compare the String values of two words.
	 * Frequency is ignored.
	 */
	@Override
	public int compareTo(Word o) {
		return this.word.compareTo(o.word);
	}
	
	@Override
	public String toString()
	{
		return word;
	}
}
