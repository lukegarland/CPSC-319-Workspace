import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 			Main class for CPSC319 Winter 2020 Assignment 4.
 * 			The main method of this class will read in a file as the only command line argument (formatted to the 
 * 			specifications of Assignment 4), and will create a graph from this file. 
 *			Then, the main method will print the graph using various methods 
 *			(specified in Graph.java) to text files.
 *
 * 			@author lukeg
 * 			@since	Apr. 8, 2020
 * 
 */

public class CPSC319W20A4 
{

	
	static Graph readFile(String filename) throws IllegalFileFormatException, IOException, FileNotFoundException
	{
		BufferedReader inputFile;

		
		inputFile = new BufferedReader(new FileReader(filename));
	
		int rows, cols, numOfNeighbourhoods;
		
		try
		{
			String[] line1 = inputFile.readLine().split("\\s+");
			String[] line2 = inputFile.readLine().split("\\s+");
			
			rows = Integer.parseInt(line1[0]);
			cols = Integer.parseInt(line1[1]);
			numOfNeighbourhoods = Integer.parseInt(line2[0]);

		}catch(NumberFormatException e)
		{
			inputFile.close();
			throw new IllegalFileFormatException("Incorrect file header format.");
		}

		Graph g = new Graph(numOfNeighbourhoods);
		
		String line = inputFile.readLine();
		String[] lineTokens;
		
		try
		{
			while(true)
			{
				lineTokens = line.split("\\s+");
				
				if(lineTokens.length != 1) 	// If lineTokens.length == 1 Neighborhood id is read. Note, the id is ignored and skipped, because if the id's are out of order
				{							// MST will fail. So, the Graph.insert() function will assign each matrix an id according to the
											// order it is inserted. This is so you can quickly reference a vertices' id to get it's location in the adjacency matrix.
											// if out-of-order id's are desired, this line is where you would read the id, and insert must take int id as an argument.	
					inputFile.close();
					throw new IllegalFileFormatException("Expected to read the id of the graph");
				}
									
				int [][] mat = new int[rows][cols]; // Matrix to add to graph
				
				for(int i = 0; i < rows ; i++) // Repeat for all rows
				{
					line = inputFile.readLine(); // read row of numbers

					if(line == null) // EOF
					{
						inputFile.close();
						throw new IllegalFileFormatException("Unexpected EOF/End of input stream");
					}
					
					lineTokens = line.split("\\s+"); // split columns

					if (lineTokens.length != cols)
					{
						inputFile.close();
						throw new IllegalFileFormatException("Incorrect number of columns in matrix");
					}
					

					for(int j = 0; j<cols; j++)
					{
						mat[i][j] = Integer.parseInt(lineTokens[j]); // import matrix values
					}
				}
				
				
				g.insert(mat);
				
				
				line = inputFile.readLine();
				if(line == null) // EOF
					break;
			}
				
		}
		catch(NumberFormatException e) // Probably thrown from Integer.ParseInt(String)		
		{
			inputFile.close();
			throw new IllegalFileFormatException("Expected int, could not parse int.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
		inputFile.close();
		return g;	
	}
	
	public static void main(String[] args)
	{
		if(args.length != 1)
		{
			System.out.println("Input file must be first and only command line argument");
			return;
		}
		String filename = args[0];
		String prefix = filename.split("_")[0];


		String graphFileName = prefix.concat("_GRAPH.txt");
		String dftFileName = prefix.concat("_DFT.txt");
		String mstFileName = prefix.concat("_MST.txt");

		Graph g;

		try
		{
			System.out.println("Reading file from " + filename);
			
			g = readFile(filename);
			
			System.out.println("Writing graph to: " + graphFileName);
			g.printGraph(new PrintWriter(new FileOutputStream(graphFileName), true));
			
			System.out.println("Writing DFT to: " + graphFileName);
			g.printDFT(new PrintWriter(new FileOutputStream(dftFileName), true));
			
			System.out.println("Writing MST to: " + graphFileName);
			g.printMST(new PrintWriter(new FileOutputStream(mstFileName), true));
			
		} catch (IllegalFileFormatException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
