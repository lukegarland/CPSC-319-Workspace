import java.util.ArrayList;
import java.util.Arrays;
/**
 * 			FibonacciGenerator.java
 * 			@author lukeg
 * 		 	@since	Jan. 24, 2020
 * 
 */

public class FibonacciGenerator {

	
	public static void main(String[] args) {
		
		runExperimentalStudyListFormat(60_000_000);
		//testAlgorithms();
		//runExperimentalStudyTableFormat(200_000_000, 100);
	}
	
	
	/**
	 * Fibonacci number generator that uses the recursive method.
	 * 
	 * @param n input
	 * @return n-th Fibonacci number
	 */
	public static long fibRec(int n)
	{
		if (n == 0 )
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibRec(n - 1) + fibRec(n - 2);		
	}

	/**
	 * Fibonacci number generator that uses recursion and memoization.
	 * 
	 * @param n input
	 * @return n-th Fibonacci number
	 */
	public static long fibMem(int n)
	{
		ArrayList<Long>memoCache = new ArrayList<Long>(n); 
		memoCache.add((long) 0); 
		memoCache.add((long) 1);
		
		if(n <= 1)
			return memoCache.get(n); 
		else 
		{
			long value = fibMem(n - 1,memoCache) + fibMem(n - 2, memoCache);
			memoCache.add(value);
			return value;	
		}
	}
	

	/**
	 * Helper method for fibMem(n). 
	 * If cache.get(n) exists, then it should return fib(n).
	 * 
	 * @param n input
	 * @param cache ArrayList of previous values calculated. 
	 * @return n-th Fibonacci number
	 */
	private static long fibMem(int n, ArrayList<Long> cache)
	{

		if(n <= cache.size() - 1 ) // if cache.get(n) exists
			return cache.get(n);
		else 
		{
			long value = fibMem(n - 1, cache) + fibMem(n - 2, cache);
			cache.add(value);
			return value;	
		}
	}

	/**
	 * Fibonacci number generator that uses a dynamic/bottom-up algorithm.
	 * 
	 * @param n input
	 * @return n-th Fibonacci number
	 */
	public static long fibDyn(int n)
	{
		long[] dynamicArray = new long[n+2];							// cost = 1
		dynamicArray[0] = 0L;											// cost = 1
		dynamicArray[1] = 1L;											// cost = 1
		
		assert n < dynamicArray.length;									// cost = 2
		
		long value = 0;													// cost = 1
		
		if (n < 2)														// cost = 1
			value = dynamicArray[n]; 									// cost = 2
		else
		{																// i = 2 -> cost  = 1
			for(int i = 2; i <= n; i++)									// cost = 1, n times 
			{
				value = dynamicArray[i - 1] + dynamicArray[i - 2];		// cost = 4, n-1 times
				dynamicArray[i] = value;								// cost = 4, n-1 times
			}															// i++ -> cost = 2, n-1 times
		}
		
		return value;													//cost = 1
		
																		// TOTAL worst case f(n) = 1+1+1+2+1+1+1+1*(n)+4*(n-1)+4*(n-1)+2*(n-1)+1
																		// f(n) = 11*n-2
	}

	/**
	 * Fibonacci number generator that uses an iteration algorithm.
	 * 
	 * @param n input
	 * @return n-th Fibonacci number
	 */
	public static long fibIter(int n)
	{
		long n_1 = 1; 						// cost = 1
		long n_2 = 0;						// cost = 1
		long value = 0;						// cost = 1
				
		if (n == 0)							// cost = 1
			return 0;
		else if (n == 1)					// cost = 1
			return 1;
		else								
		{									// i = 2 -> cost  = 1
			for(int i = 2; i <= n; i++)		// cost = 1, n times 
			{
				value = n_1 + n_2;			// cost = 2, n-1 times
				n_2 = n_1;					// cost = 1, n-1 times
				n_1 = value;				// cost = 1, n-1 times
			}								// i++ -> cost = 2, n-1 times
			return value;					// cost = 1
		}
		
		
											// TOTAL worst case f(n) = 1+1+1+1+1+1+1*n+2*(n-1)+1*(n-1)+1*(n-1)+2*(n-1)+1
											// f(n) = 7n + 1
		
	}
	
	/**
	 * Fibonacci number generator that uses matrix exponentiation.
	 * 
	 * @param n input
	 * @return n-th Fibonacci number
	 */
	public static long fibMat(int n)
	{
		if(n == 0)							// cost = 1
			return 0;						
		long [][] FM = new long[2][2];		// cost = 1

		// Initialize FM
		FM[0][0] = 1;						// cost = 1				
		FM[0][1] = 1;						// cost = 1
		FM[1][0] = 1;						// cost = 1
		FM[1][1] = 0;						// cost = 1
											
		
											// Let running time of matrixPower(n) = g(n)
		matrixPower(n-1, FM);				// cost = 2 + g(n-1)
											// 		= 2 + 67*log(n-1) + 1
		
		return FM[0][0];					// cost = 1
		
											
											// TOTAL worst case f(n) = 6 + 2 + 67log(n-1) + 1
											// f(n) = 67log(n-1) + 9
	}
	
	/**
	 * Helper method for fibMat(n). Computes the n-th power of the 2x2 matrix FM.
	 * To calculate the (n-1)-th fibonacci number, then FM should be inialized to {{1,1}, {1,0}} 
	 * 
	 * @param n input
	 * @param FM 2x2 matrix of type long
	 */
	private static void matrixPower(int n,long[][] FM )
	{
		if(n > 1) //	cost = 1 
		{
			matrixPower(n/2, FM); //	cost = g(n/2)
			
			//FM = FM * FM 
			long a = FM[0][0] * FM[0][0]  + FM[0][1] * FM[1][0]; 	//cost = 8
			long b = FM[0][0] * FM[0][1]  + FM[0][1] * FM[1][1]; 	//cost = 8
			long c = FM[1][0] * FM[0][0]  + FM[1][1] * FM[1][0]; 	//cost = 8
			long d = FM[1][0] * FM[0][1]  + FM[1][1] * FM[1][1]; 	//cost = 8
			FM[0][0] = a; 											//cost = 1
			FM[0][1] = b; 											//cost = 1
			FM[1][0] = c; 											//cost = 1
			FM[1][1] = d; 											//cost = 1

			if(n % 2 != 0)											//cost = 2
			{	//If n is odd -> FM * [1,1][1,0]
				long a1 = FM[0][0] * 1  + FM[0][1] * 1; 			// cost = 6
				long b1 = FM[0][0] * 1  + FM[0][1] * 0;				// cost = 6
				long c1 = FM[1][0] * 1  + FM[1][1] * 1;				// cost = 6
				long d1 = FM[1][0] * 1  + FM[1][1] * 0;				// cost = 6
				FM[0][0] = a1;										// cost = 1
				FM[0][1] = b1;										// cost = 1
				FM[1][0] = c1;										// cost = 1
				FM[1][1] = d1;										// cost = 1
			}
		}
		
																	// g(n) = 1 			if n = 1
																	// 		= g(n/2)+67 	otherwise
																	
																	// by using substitution ... g(n) = g(n/2^k) + 67k, and log(n) = k
																	// g(n) = 67*log(n) + 1
	}	
	
	/**
	 * Runs an experimental study of the runtimes for fibRec(n), fibMem(n), fibDyn(n), fibIter(n), and fibMat(n).
	 * Will print out the average execution time in ns for some values of n up to maxValue.
	 * n will step up by 1 until n == 40, then n = 100, then n doubles each time thereafter.
	 * For FibRec(n), n will never exceed 40, and for FibMem(n) n will never exceed 6400. All other functions are guaranteed to print up to (but not including maxValue) 
	 * 
	 * @param maxValue maximum fibonacci value to calculate
	 * @param numberOfCalls number of times each algorithm will be called before n is incremented
	 */
	private static void runExperimentalStudyTableFormat(int maxValue, int numberOfCalls)
	{
		long [] results = new long[5];

		System.out.println("n\t          fibRec(n)\t     fibMem(n)\t      fibDyn(n)\t     fibIter(n)\t        fibMat(n)");
		System.out.println("-------------------------------------------------------------------------------------------------");
		
		long timeStart;
		int n = 0;
		
		while(n < maxValue)
		{
			Arrays.fill(results, 0);
			System.out.printf("%-8d\t", n);

			for (int j = 0; j < numberOfCalls; j++)
			{
				if(n <= 40) //Over n = 40 fibRec takes too long
				{
					timeStart = System.nanoTime();
					fibRec(n);
					results[0] += System.nanoTime() - timeStart;
				}

				if(n <= 6400) //Do not run memoization after 6400 as StackOverFlowError exception will be thrown for n = 12800 
				{
					timeStart = System.nanoTime();
					fibMem(n);
					results[1] += System.nanoTime() - timeStart;
				}

				timeStart = System.nanoTime();
				fibDyn(n);
				results[2] += System.nanoTime() - timeStart;

				timeStart = System.nanoTime();
				fibIter(n);
				results[3] += System.nanoTime() - timeStart;

				if(n > 50) 	// Matrix method is not timed under an input of 50 as 
				{			// the values were very noisy and unpredictable.
					timeStart = System.nanoTime();
					fibMat(n);
					results[4] += System.nanoTime() - timeStart;
				}

			}

			System.out.printf("%10d\t", results[0]/numberOfCalls);
			System.out.printf("%14d\t", results[1]/numberOfCalls);
			System.out.printf("%14d\t", results[2]/numberOfCalls);
			System.out.printf("%14d\t", results[3]/numberOfCalls);
			System.out.printf("%14d\t", results[4]/numberOfCalls);
			System.out.println();


			if(n < 40)
				n++;
			else if (n < 100)
				n = 100;
			else 
				n *= 2;
		}
	}
	
	
	/**
	 * Prints the running time for fibRec(n), fibMem(n), fibDyn(n), fibIter(n), and fibMat(n) in a list for certain values of n.
	 * n will step up by 1 until n == 40, then n = 100, then n doubles each time thereafter.
	 * For FibRec(n), n will never exceed 40, and for FibMem(n) n will never exceed 6400. All other functions are guaranteed to print up to (but not including maxValue) 
	 * 
	 * 
	 * @param maxValue maximum input value
	 */
	private static void runExperimentalStudyListFormat(int maxValue)
	{
		int n;
		long timeStart;
		
		
		System.out.println("n       time");
		
		//Recursive
		n = 0;
		while(n < maxValue && n <= 40) //Over n = 40 fibRec takes too long
		{
			
		
			timeStart = System.nanoTime();
			fibRec(n);
			System.out.printf("%-8d %d\n", n, System.nanoTime() - timeStart);

			n++;
			
		}
		
		//Memoization
		n = 0;
		System.out.println();
		while(n < maxValue &&  n < 6400) //Do not run memoization after 6400 as StackOverFlowError exception will be thrown for n = 6400
		{
			

			timeStart = System.nanoTime();
			fibMem(n);
			System.out.printf("%-8d %d\n", n, System.nanoTime() - timeStart);
			
			if(n < 40)
				n++;
			else if (n < 100)
				n = 100;
			else 
				n *= 2;
		}
		
		//Dynamic
		n = 0;
		System.out.println();
		while(n < maxValue)
		{

			timeStart = System.nanoTime();
			fibDyn(n);
			System.out.printf("%-8d %d\n", n, System.nanoTime() - timeStart);
			
			if(n < 40)
				n++;
			else if (n < 100)
				n = 100;
			else 
				n *= 2;
		}
		
		//Iteration
		n = 0;
		System.out.println();
		while(n < maxValue)
		{

			timeStart = System.nanoTime();
			fibIter(n);
			System.out.printf("%-8d %d\n", n, System.nanoTime() - timeStart);
			
			if(n < 40)
				n++;
			else if (n < 100)
				n = 100;
			else 
				n *= 2;
		}
		
		//Matrix
		n = 0;
		System.out.println();
		while(n < maxValue)
		{

			timeStart = System.nanoTime();
			fibMat(n);
			System.out.printf("%-8d %d\n", n, System.nanoTime() - timeStart);
			
			if(n < 40)
				n++;
			else if (n < 100)
				n = 100;
			else 
				n *= 2;
		}
		System.out.println();

	}
	
	/**
	 * Tests various fibonacci algorithms.
	 */
	private static void testAlgorithms() 
	{
		testFibRec();
		testFibMem();
		testfibDyn();
		testFibIter();	
		testFibMat();
	}
	
	
	
	//==========================Testing Algorithms=================================//
	
	/**
	 * Tests fibRec(n) for some values of n and prints the result.
	 */
	private static void testFibRec()
	{
		System.out.println("Recursive");

		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 40; i++)
		{
			System.out.printf("%-8d%d\n", i, fibRec(i));
		}
	}
	
	
	
	/**
	 * Tests fibMem(n) for some values of n and prints the result.
	 */
	private static void testFibMem()
	{
		System.out.println("\nMemoization");

		System.out.println("\n       fib(n)");
		System.out.println("--------------");
		
		for (int i = 0; i < 50; i++)
		{
			System.out.printf("%-8d%d\n", i, fibMem(i));
		}
		
	}
	
	
	/**
	 * Tests fibDyn(n) for some values of n and prints the result.
	 */
	private static void testfibDyn()
	{
		System.out.println("\nDynamic");

		System.out.println("n       fib(n)");
		System.out.println("--------------");
		
		for (int i = 0; i < 50; i++)
		{
			System.out.printf("%-8d%d\n", i, fibDyn(i));
		}
	}
	
	
	/**
	 * Tests fibIter(n) for some values of n and prints the result.
	 */
	private static void testFibIter()
	{
		System.out.println("\nIteration");

		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 50; i++)
		{
			System.out.printf("%-8d%d\n", i, fibIter(i));
		}
	}
	
	
	/**
	 * Tests fibMat(n) for some values of n and prints the result.
	 */
	private static void testFibMat()
	{
		System.out.println("\nMatrix Exponentiation");

		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 50; i++)
		{
			System.out.printf("%-8d%d\n", i, fibMat(i));
		}
	}
	

}
