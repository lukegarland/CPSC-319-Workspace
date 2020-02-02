import java.util.ArrayList;
import java.util.Arrays;
/**
 * 			FibonacciGenerator.java
 * Author: 	@author lukeg
 * Date: 	@date	Jan. 24, 2020
 * 
 */

public class FibonacciGenerator {


	
	
	public FibonacciGenerator()
	{


	}
	
	public static long fibRec(int n)
	{
		if (n == 0 )
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibRec(n - 1) + fibRec(n - 2);		
	}

	
	public static long fibMem(int n)
	{
		ArrayList<Long>memoCache = new ArrayList<Long>();
		memoCache.add((long) 0);
		memoCache.add((long) 1);
		
		if(n <= memoCache.size() - 1 )
		{
			return memoCache.get(n);
		}
		else 
		{
			long value = fibMem(n - 1,memoCache) + fibMem(n - 2, memoCache);
			memoCache.add(value);
			return value;	
		}
	}
	private static long fibMem(int n, ArrayList<Long> cache)
	{

		if(n <= cache.size() - 1 )
		{
			return cache.get(n);
		}
		else 
		{
			long value = fibMem(n - 1, cache) + fibMem(n - 2, cache);
			cache.add(value);
			return value;	
		}
	}

	
	public static long fibDyn(int n)
	{
		long[] dynamicArray = new long[n+2];
		dynamicArray[0] = 0L;
		dynamicArray[1] = 1L;
		
		assert n < dynamicArray.length;
		
		long value = 0;
		if (n < 2)
			value = dynamicArray[n];
		else
		{	
			for(int i = 2; i <= n; i++)
			{
				value = dynamicArray[i - 1] + dynamicArray[i - 2];
				dynamicArray[i] = value;
			}
		}
		return value;
	}

	
	public static long fibIter(int n)
	{
		long n_1 = 1;
		long n_2 = 0;
		long value = 0;
		
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
		{
			for(int i = 2; i <= n; i++)
			{
				value = n_1 + n_2;
				n_2 = n_1;
				n_1 = value;
			}
			return value;
		}
	}
	
	
	public static long fibMat(int n)
	{
		if(n == 0)
			return 0;
		long [][] FM = new long[2][2];

		// Initialize FM
		FM[0][0] = 1;
		FM[0][1] = 1;
		FM[1][0] = 1;
		FM[1][1] = 0;
		
		matrixPower(n-1, FM);
		
		return FM[0][0];		
	}
	
	private static void matrixPower(int n,long[][] FM )
	{
		if(n > 1)
		{
			matrixPower(n/2, FM);
			
			//FM = FM * FM 
			long a = FM[0][0] * FM[0][0]  + FM[0][1] * FM[1][0];
			long b = FM[0][0] * FM[0][1]  + FM[0][1] * FM[1][1];
			long c = FM[1][0] * FM[0][0]  + FM[1][1] * FM[1][0];
			long d = FM[1][0] * FM[0][1]  + FM[1][1] * FM[1][1];
			FM[0][0] = a;
			FM[0][1] = b;
			FM[1][0] = c;
			FM[1][1] = d;

			if(n % 2 != 0)
			{	//If n is odd -> FM * [1,1][1,0]
				long a1 = FM[0][0] * 1  + FM[0][1] * 1;
				long b1 = FM[0][0] * 1  + FM[0][1] * 0;
				long c1 = FM[1][0] * 1  + FM[1][1] * 1;
				long d1 = FM[1][0] * 1  + FM[1][1] * 0;
				FM[0][0] = a1;
				FM[0][1] = b1;
				FM[1][0] = c1;
				FM[1][1] = d1;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int numberOfCalls = 100;
		long [] results = new long[5];
		
		System.out.println("n          fibRec(n)\t          fibMem(n)\t          fibDyn(n)\t          fibIter(n)\t          fibMat(n)");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		long timeStart;
		
		for(int i = 0; i< 30000000; )
		{
			Arrays.fill(results, 0);
			System.out.printf("%-8d\t",i);

			for (int j = 0; j < numberOfCalls; j++)
			{
				if(i<=30)
				{
					timeStart = System.nanoTime();
					fibRec(i);
					results[0] += System.nanoTime() - timeStart;
				}
				
				if(i <= 6400)
				{
					timeStart = System.nanoTime();
					fibMem(i);
					results[1] += System.nanoTime() - timeStart;
				}

				timeStart = System.nanoTime();
				fibDyn(i);
				results[2] += System.nanoTime() - timeStart;

				timeStart = System.nanoTime();
				fibIter(i);
				results[3] += System.nanoTime() - timeStart;

				timeStart = System.nanoTime();
				fibMat(i);
				results[4] += System.nanoTime() - timeStart;
			}
			
			System.out.printf("%10d\t", results[0]/numberOfCalls);
			System.out.printf("%16d\t", results[1]/numberOfCalls);
			System.out.printf("%16d\t", results[2]/numberOfCalls);
			System.out.printf("%16d\t", results[3]/numberOfCalls);
			System.out.printf("%16d\t", results[4]/numberOfCalls);

			System.out.println();
			if(i<40)
				i++;
			else if (i<100)
				i+=10;
			else if (i <= 6400)
				i *= 2;
			else if(i == 12800)
				i = 100000;
			else
				i+=1000000;	
		}
		
//		testFibRec();
//		
//		testFibMem();
//		
//		testfibDyn();
//		
//		testFibIter();
//		
		testFibMat();
//		
	}
	
	private static void testFibRec()
	{
		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 45; i++)
		{
			System.out.printf("%-8d%d\n", i, fibRec(i));
		}
	}
	
	private static void testFibMem()
	{
		System.out.println("\nn       fib(n)");
		System.out.println("--------------");
		
		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, fibMem(i));
		}
		
	}
	
	private static void testfibDyn()
	{
		System.out.println("\nn       fib(n)");
		System.out.println("--------------");
		
		for (int i = 0; i < 45; i++)
		{
			System.out.printf("%-8d%d\n", i, fibDyn(i));
		}
	}
	
	private static void testFibIter()
	{
		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, fibIter(i));
		}
	}
	
	private static void testFibMat()
	{
		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, fibMat(i));
		}
	}
	

}
