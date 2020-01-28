
public class Matrix {

	
	private int n, m;
	public int [][] a; 
	
	public Matrix(int n, int m)
	{
		this.n = n;
		this.m = m;
		a = new int[n][m];
	}
	
	public void add(Matrix b)
	{
		assert(this.n == b.n && this.m == b.m);
			
		for (int i = 0; i< this.n; i++)
		{
			for (int j = 0; j < this.m; j++)
				this.a[i][j] = this.a[i][j] + b.a[i][j];
		}
	}
}
