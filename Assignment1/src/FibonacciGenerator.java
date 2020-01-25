import java.util.ArrayList;

/**
 * 			FibonacciGenerator.java
 * Author: 	@author lukeg
 * Date: 	@date	Jan. 24, 2020
 * 
 */

public class FibonacciGenerator {

	private ArrayList<Long>memoCache = new ArrayList<Long>();

	private long[] dynamicArray = new long[1000000];
	
	private long [][] FM = new long[2][2];
	
	public FibonacciGenerator()
	{
		memoCache.add((long) 0);
		memoCache.add((long) 1);
		
		dynamicArray[0] = 0L;
		dynamicArray[1] = 1L;
	}
	
	public long fibRec(int n)
	{
		if (n == 0 )
			return 0;
		else if (n == 1)
			return 1;
		else
			return this.fibRec(n - 1) + this.fibRec(n - 2);		
	}

	
	public long fibMem(int n)
	{
		if(n <= this.memoCache.size() - 1 )
		{
			return memoCache.get(n);
		}
		else 
		{
			long value = this.fibMem(n - 1) + this.fibMem(n - 2);
			this.memoCache.add(value);
			return value;	
		}
	}

	
	public long fibDyn(int n)
	{
		assert n < dynamicArray.length;
		
		long value = 0;
		if (n < 2)
			value = dynamicArray[n];
		else
		{	
			for(int i = 2; i <= n; i++)
			{
				value = dynamicArray[n - 1] + dynamicArray[n - 2];
				dynamicArray[n] = value;
			}
		}
		return value;
	}

	
	public long fibIter(int n)
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
	
	
	public long fibMat(int n)
	{
		if(n == 0)
			return 0;
		
		// Initialize FM
		FM[0][0] = 1;
		FM[0][1] = 1;
		FM[1][0] = 1;
		FM[1][1] = 0;
		
		this.matrixPower(n-1);
		
		return FM[0][0];		
	}
	
	private void matrixPower(int n)
	{
		if(n > 1)
		{
			this.matrixPower(n/2);
			
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
		final int numberOfCalls = 3;
		
		FibonacciGenerator generate = new FibonacciGenerator();
		System.out.println("n          fibRec(n)          fibMem(n)          fibDyn(n)          fibIter(n)          fibMat(n)");
		System.out.println("-------------------------------------------------------------------------------------------------");
		long timeStart, timeEnd;
		
		for(int i = 1; i< 1000000; )
		{
			System.out.printf("%-7d\t",i);
			if(i<43)
			{
				timeStart = System.nanoTime();
				for (int j = 0; j< numberOfCalls; j++)
					generate.fibRec(i);
				timeEnd = System.nanoTime();
				System.out.printf("%10d\t", (timeEnd - timeStart)/numberOfCalls);
			}
			else
			{
				System.out.printf("%10d\t", 0);
			}
			
			if(i <= 6400)
			{
				timeStart = System.nanoTime();
				for (int j = 0; j< numberOfCalls; j++)
					generate.fibMem(i);
				
				timeEnd = System.nanoTime();
				System.out.printf("%16d\t", (timeEnd - timeStart)/numberOfCalls);			
			}else
				System.out.printf("%16d\t", 0);

			timeStart = System.nanoTime();
			for (int j = 0; j< numberOfCalls; j++)
				generate.fibDyn(i);
			timeEnd = System.nanoTime();
			System.out.printf("%16d\t", (timeEnd - timeStart)/numberOfCalls);
			
			timeStart = System.nanoTime();
			for (int j = 0; j< numberOfCalls; j++)
				generate.fibIter(i);
			timeEnd = System.nanoTime();
			System.out.printf("%16d\t", (timeEnd - timeStart)/numberOfCalls);
			
			timeStart = System.nanoTime();
			for (int j = 0; j< numberOfCalls; j++)
				generate.fibMat(i);
			timeEnd = System.nanoTime();
			System.out.printf("%16d\t", (timeEnd - timeStart)/numberOfCalls);
			
			System.out.println();
			if(i<45)
				i++;
			else if (i<100)
				i+=5;
			else
				i*=2;
				
		}
//		generate.testFibRec();
//		
//		generate.testFibMem();
//		
//		generate.testfibDyn();
//		
//		generate.testFibIter();
//		
//		generate.testFibMat();
//		
	}
	
	private void testFibRec()
	{
		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 45; i++)
		{
			System.out.printf("%-8d%d\n", i, this.fibRec(i));
		}
	}
	
	private void testFibMem()
	{
		System.out.println("\nn       fib(n)");
		System.out.println("--------------");
		
		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, this.fibMem(i));
		}
		
	}
	
	private void testfibDyn()
	{
		System.out.println("\nn       fib(n)");
		System.out.println("--------------");
		
		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, this.fibDyn(i));
		}
	}
	
	private void testFibIter()
	{
		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, this.fibIter(i));
		}
	}
	
	private void testFibMat()
	{
		System.out.println("n       fib(n)");
		System.out.println("--------------");

		for (int i = 0; i < 100; i++)
		{
			System.out.printf("%-8d%d\n", i, this.fibMat(i));
		}
	}
	

}
