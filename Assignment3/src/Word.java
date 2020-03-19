/**
 * 			Word.java
 * 			@author lukeg
 * 			@since	Mar. 18, 2020
 * 
 */

public class Word implements Comparable<Word>{

	
	private String word;
	private int frequency;
	
	
	public Word(String word)
	{
		this.word = word;
		this.frequency = 1;
	}
	
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	
	
	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	public void increment()
	{
		this.frequency++;
	}
	

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
