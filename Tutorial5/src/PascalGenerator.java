import java.math.BigDecimal;
import java.math.BigInteger;

public class PascalGenerator {

	
	public static long pascalRecursive(int n, int k)
	{
		if(k == n || k == 0)
			return 1L;
		else
			return pascalRecursive(n-1, k) + pascalRecursive(n-1, k-1);
	}
	
	
	public static long pascalLoops(int n, int k)
	{
		assert(k <= n);
		
		if (n == 0)
			return 1;
		
		long[] previousRow = {1};
		long[] rowN = {1,1};
		
		for(int i = 2; i <= n; i++)
		{
			previousRow = rowN;
			rowN = new long[i+1];
			rowN[0] = 1;
			rowN[i] = 1;
			
			for(int j = 1; j < i; j++)
			{
				rowN[j] =previousRow[j] + previousRow[j-1];
			}
		}
		return rowN[k];
	}
	
	
	public static long pascalFactorial(int n, int k) 
	{
		BigInteger den = factorial(k).multiply(factorial(n-k));
		return factorial(n).divide(den).longValue();
	}
	
	public static BigInteger factorial(int n) {
	    BigInteger result = BigInteger.ONE;
	    for (int i = 2; i <= n; i++)
	        result = result.multiply(BigInteger.valueOf(i));
	    return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				pascalFactorial(i, j);

			}
			System.out.printf("%d\t%d\n", i, System.nanoTime() - startTime);

		}
		
//		for(int i = 0; i < 30; i++)
//		{
//			for (int j = 0; j <= i; j++)
//			{
//				pascalRecursive(i, j);
//
//			}
//			System.out.printf("%d\t%d\n", i, System.nanoTime() - startTime);
//
//		}
//		System.out.printf("Recursive OVER");
//
//		startTime = System.nanoTime();
//		for(int i = 0; i < 100; i++)
//		{
//			for (int j = 0; j <= i; j++)
//			{
//				pascalLoops(i, j);
//
//			}
//			System.out.printf("%d\t%d\n", i, System.nanoTime() - startTime);
//
//		}
		
		
	}

}
