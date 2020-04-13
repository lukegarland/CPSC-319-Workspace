import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 			Graph type for CPSC319 Winter 2020 Assignment 4.
 * 			This class stores a weighted undirected graph with different printing methods. 
 * 			The value of each vertex is a 2-dimensional array of integers, and the weight between vertices is the total difference between each pair of points in the 2-d arrays.
 * 
 *  		This class has the following printing methods:
 *  		- Print all edges in ascending order.
 *  		- Print the edges in a depth first traversal.
 *  		- Print the edges of the minimum spanning tree of the graph. 
 *  
 * 			@author lukeg
 * 			@since	Apr. 8, 2020
 * 
 */

/**
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
	 * Note: adjMatrix[i][j] represents the weight of the connection between vertex vertexArray[i] and the vertex vertexArray[j].
	 */
	private int[][] adjMatrix;


	/**
	 * List of all vertices.
	 */
	private Vertex[] vertexArray;
	
	
	/**
	 * Class to store vertices in the graph. The value of the graph is int[][] by default.
	 */
	private class Vertex
	{
		int mat[][];
		int id;
		
		public Vertex(int[][] mat, int id)
		{
			this.mat = mat;
			this.id = id;
		}
		
		/**
		 * Calculates the weight between the vertex and vertex v.
		 * The weight is calculated by taking the difference between each pair of points in each vertex's 
		 * mat and summing these differences together.
		 * @param v second vertex
		 * @return Weight between 'this' vertex and vertex v.
		 */
		public int calcDifference(Vertex v)
		{
			int sum = 0;
			
			for(int i = 0; i < this.mat.length; i++)
				for(int j = 0; j < this.mat[0].length; j++)
					sum += Math.abs(this.mat[i][j] - v.mat[i][j]);
			
			return sum;
		}
	}

	
	/**
	 * Constructor for a graph with maximum size n
	 */
	public Graph(int n)
	{
		size = 0;
		adjMatrix = new int[n][n];
		vertexArray = new Vertex[n];
	}
	
	
	/**
	 * @return number of vertices currently in the graph.
	 */
	public int getSize()
	{
		return size;
	}

	
	/**
	 * Inserts a new vertex with value {@code mat} into the graph. 
	 * @param mat
	 * @throws IndexOutOfBoundsException If graph is already full
	 */
	public void insert(int[][] mat) throws IndexOutOfBoundsException
	{
		Vertex v = new Vertex(mat, size); // Note: id is automatically assigned.
		
		if(size >= vertexArray.length) // Catch an imminent IndexOutOfBoundsException, and give a more descriptive message.
			throw new IndexOutOfBoundsException("No more space allocated in graph to insert a vertex.");
		
		vertexArray[size] = v;
		calcAdjMatrix(size); // Update adjacency matrix at the newest index.
		
		size++; //Update size

	}
	
	/**
	 * Calculates (and updates) the adjacency matrix at row (and column) i when a new vertex is inserted.
	 * For example, when inserting the 3rd vertex (i.e. adding a vertex with id == 2), calcAdjMatrix(2) should be invoked.
	 * @param i Row (and column) to update.
	 */
	private void calcAdjMatrix(int i)
	{

		int diff;
		Vertex current = vertexArray[i]; // Vertex at i (to update)
		
		for(int j = 0; j < size; j++) 	// Loop through adjacency matrix values that need updating 
		{								// (adjMatrix[i][0 thru size] and adjMatrix[0 thru size][i])
		
			diff = current.calcDifference(vertexArray[j]);

			// Undirected graph
			adjMatrix[i][j] = diff;
			adjMatrix[j][i] = diff;
		}

	}
	
	
	
	/**
	 * Prints the weight of every edge of the graph to out in increasing order.  
	 * @param out Output stream to print to.
	 */
	public void printGraph(PrintWriter out)
	{
		Vertex current;
		out.println("Edge\tWeight");
		for(int i = 0; i < size; i++)
		{
			current = vertexArray[i];
			for(int j = i + 1; j < size; j++) // Skip values j <= i, as adjMatrix[i][j] == adjMatrix[j][i]
			{
				out.println(String.format("%d - %d\t%d",current.id, vertexArray[j].id, adjMatrix[i][j]));
			}
		}
	}
	
	/**
	 * Prints the weight of the edges of the graph in depth first traversal order to out.
	 * @param out Output stream to print to.
	 */
	public void printDFT(PrintWriter out)
	{
		ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>(size);
		out.println("Edge\tWeight");
		depthFirstSearch(out, visitedVertices, 0);
	}
	
	/**
	 * Recursive helper function to traverse graph in DFT order.
	 * @param out Output stream to print to.d
	 * @param visited List of visited vertices. Should be empty on first call.
	 * @param i current id of the vertex that is being visited.
	 */
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
			out.println(String.format("%d - %d\t%d",current.id, vertexArray[j].id, adjMatrix[i][j]));
			

			depthFirstSearch(out, visited, ++i);
		}
	}
	
	/**
	 *  
	 * Prints the weight of the edges of the graph as a Minimum spanning tree to out.
	 * 
     * Implementation from :	"Prim's Minimum Spanning Tree (MST): Greedy Algo-5," GeeksforGeeks, 07-Aug-2019. 
	 * 							[Online]. Available: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/. 
	 * 							[Accessed: 13-Apr-2020].
	 * 
	 * @param out Output stream to print to.
	 */
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
	
    /**
     * A utility function to find the vertex with minimum value from the set of vertices that are not yet included in MST.
     * 
     * Implementation from :	"Prim's Minimum Spanning Tree (MST): Greedy Algo-5," GeeksforGeeks, 07-Aug-2019. 
	 * 							[Online]. Available: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/. 
	 * 							[Accessed: 13-Apr-2020].
	 * 
     * @param key Key values used to pick minimum weight.
     * @param mstSet set of vertices not yet included in MST 
     * @return the index of the vertex with minimum value that is not yet included in the MST.
     */
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
	
	/**
	 * Helper function to print the MST stored in parent.
	 * 
     * Implementation from :	"Prim's Minimum Spanning Tree (MST): Greedy Algo-5," GeeksforGeeks, 07-Aug-2019. 
	 * 							[Online]. Available: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/. 
	 * 							[Accessed: 13-Apr-2020].
	 * 
	 * @param out Output stream to print to.
	 * @param parent Array containing the MST of the graph.
	 */
	private void printMST(PrintWriter out, Vertex parent[])
	{
		out.println("Edge\tWeight");
		for(int i = 1; i < size; i++)
		{
			out.println(String.format("%d - %d\t%d",parent[i].id, i, adjMatrix[i][parent[i].id]));
		}		
	}
}
