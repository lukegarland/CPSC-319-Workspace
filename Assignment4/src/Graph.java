import java.io.PrintWriter;

/**
 * 			Graph.java
 * 			@author lukeg
 * 			@since	Apr. 8, 2020
 * 
 */

public class Graph
{
	
	
	@SuppressWarnings("unused")
	private class Vertex
	{
		int mat[][];
		int id;
		
		public Vertex(int[][] mat, int id)
		{
			this.mat = mat;
			this.id = id;
		}
		
		public int calcDifference(Vertex v)
		{
			int sum = 0, diff;
			
			for(int i = 0; i < this.mat.length; i++)
			{
				for(int j = 0; j < this.mat[0].length; j++)
				{
					diff = this.mat[i][j] - v.mat[i][j];
					sum += Math.abs(diff);
				}
			}
			
			return sum;
			
		}
	}
	
	int size;
	int[][] adjMatrix;
	Vertex[] neighbourhoods;
	
	
	public Graph(int n)
	{
		size = 0;
		adjMatrix = new int[n][n];
		neighbourhoods = new Vertex[n];
	}
	
	void insert(int[][] mat, int id)
	{
		Vertex v = new Vertex(mat, id);
		neighbourhoods[size++] = v;
	}
	
	void calcAdjMatrix()
	{
		
		int diff;
		Vertex current;
		for(int i = 0; i < neighbourhoods.length; i++)
		{
			current = neighbourhoods[i];
			for(int j = i + 1; j < neighbourhoods.length; j++)
			{
				diff = current.calcDifference(neighbourhoods[j]);
				adjMatrix[i][j] = diff;
				adjMatrix[j][i] = diff;
			}
		}
	}
	
	void printGraph(PrintWriter out)
	{
		Vertex current;
		out.println("Edge\tWeight");
		for(int i = 0; i < adjMatrix.length; i++)
		{
			current = neighbourhoods[i];
			for(int j = i + 1; j < adjMatrix.length; j++)
			{
				out.printf("%d - %d\t%d",current.id, neighbourhoods[j].id, adjMatrix[i][j]);
				if(i != adjMatrix.length - 2 || j != adjMatrix.length - 1)
					out.print('\n');
			}
		}
		
		out.flush();
	}
	
	
}
