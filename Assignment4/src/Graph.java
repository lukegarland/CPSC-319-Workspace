import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 			Graph.java
 * 			@author lukeg
 * 			@since	Apr. 8, 2020
 * 
 */

public class Graph
{
	
	
	/**
	 * Number of Vertices.
	 */
	private int size;
	

	/**
	 * Adjacency matrix containing weighted connections between vertices.
	 */
	private int[][] adjMatrix;


	/**
	 * List of all vertices.
	 */
	private Vertex[] vertexArray;
	
	
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
			int sum = 0;
			
			for(int i = 0; i < this.mat.length; i++)
				for(int j = 0; j < this.mat[0].length; j++)
					sum += Math.abs(this.mat[i][j] - v.mat[i][j]);
			
			return sum;
		}
	}

	
	public Graph(int n)
	{
		size = 0;
		adjMatrix = new int[n][n];
		vertexArray = new Vertex[n];
	}
	
	
	/**
	 * @return the size
	 */
	public int getSize()
	{
		return size;
	}

	
	public void insert(int[][] mat)
	{
		Vertex v = new Vertex(mat, size);
		vertexArray[size] = v;
		size++;
		calcAdjMatrix(); // Update adjacency matrix
	}
	
	private void calcAdjMatrix()
	{
		
		int diff;
		Vertex current;
		for(int i = 0; i < size; i++)
		{
			current = vertexArray[i];
			for(int j = i + 1; j < size; j++)
			{
				diff = current.calcDifference(vertexArray[j]);
				adjMatrix[i][j] = diff;
				adjMatrix[j][i] = diff;
			}
		}
	}
	
	public void printGraph(PrintWriter out)
	{
		Vertex current;
		out.println("Edge\tWeight");
		for(int i = 0; i < size; i++)
		{
			current = vertexArray[i];
			for(int j = i + 1; j < size; j++)
			{
				out.printf("%d - %d\t%d",current.id, vertexArray[j].id, adjMatrix[i][j]);
				
				if(i != size - 2 || j !=size - 1) // if NOT last vertex
					out.print('\n');
			}
		}
	}
	
	public void printDFT(PrintWriter out)
	{
		ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>(size);
		out.println("Edge\tWeight");
		depthFirstSearch(out, visitedVertices, 0);
	}
	
	private void depthFirstSearch(PrintWriter out, ArrayList<Vertex> visited, int i)
	{
		Vertex current = vertexArray[i];
		visited.add(current);
		
		for(int j = 0; j < size; j++)
		{
			if(visited.contains(vertexArray[j]) || adjMatrix[i][j] == 0) // skip if node is already visited, or if not connected
			{
				continue;
			}
			out.printf("%d - %d\t%d",current.id, vertexArray[j].id, adjMatrix[i][j]);
			
			if(visited.size() != size - 1) 	// if we have not visited all the nodes.
				out.print('\n');			// note: 'size - 1' is used as next recursive call will add last node to visited, and 
											// nothing will be printed to out.
			
			depthFirstSearch(out, visited, ++i);
		}
	}
	
	public void printMST(PrintWriter out)
	{
		Vertex[] parent = new Vertex[size];
		
		int[] key = new int[size];
		boolean mstSet[] = new boolean[size];
		
		
		for (int i = 0; i < size; i++)
		{
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		
		key[0] = 0;
		parent[0] = vertexArray[0]; // first vertex is root
		
		for(int count = 0; count < size - 1; count++)
		{
			int u = minKey(key, mstSet);
			
			mstSet[u] = true;
			
			for(int v = 0; v < size; v++)
			{
				
				if(adjMatrix[u][v] != 0 && mstSet[v] == false && adjMatrix[u][v] < key[v])
				{
					parent[v] = vertexArray[u];
					key[v] = adjMatrix[u][v];
				}
			}
			
		}
		
		printMST(out, parent);

	}
	
    private int minKey(int key[], boolean mstSet[]) 
    { 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int i = 0; i < size; i++) 
        {
        	if (mstSet[i] == false && key[i] < min) 
        
            { 
                min = key[i]; 
                min_index = i; 
            } 
        }
        return min_index; 
    } 
	
	private void printMST(PrintWriter out, Vertex parent[])
	{
		out.println("Edge\tWeight");
		for(int i = 1; i < size; i++)
		{
			out.printf("%d - %d\t%d",parent[i].id, i, adjMatrix[i][parent[i].id]);
			if(i != size - 1)
			{
				out.print('\n');
			}
		}		
	}
}
