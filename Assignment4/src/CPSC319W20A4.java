import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 			CPSC319W20A4.java
 * 			@author lukeg
 * 			@since	Apr. 8, 2020
 * 
 */

public class CPSC319W20A4 
{

	
	static Graph readFile(String filename) throws IllegalFileFormatException, IOException
	{
		BufferedReader inputFile;
		Graph g;
		try
		{
			inputFile = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}

		
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
			throw new IllegalFileFormatException();
		}
		catch(IOException e)
		{
			throw new IllegalFileFormatException();
		}
		
		

		g = new Graph(numOfNeighbourhoods);
		
		String line = inputFile.readLine();
		String[] lineTokens;
		int id = 0;
		
		try
		{
			while(true)
			{
				lineTokens = line.split("\\s+");
				
				if(lineTokens.length == 1)
					id = Integer.parseInt(lineTokens[0]);
				
				int [][] mat = new int[rows][cols];
				
				for(int i = 0; i < rows ; i++)
				{
					line = inputFile.readLine(); // row of numbers
					lineTokens = line.split("\\s+");

					if(line == null || lineTokens.length != cols)
						throw new IllegalFileFormatException();
					
					for(int j = 0; j<cols; j++)
					{
						mat[i][j] = Integer.parseInt(lineTokens[j]);
					}
				}
				
				
				g.insert(mat, id);
				
				
				line = inputFile.readLine();
				if(line == null) // EOF
					break;
			}
				
		}catch(NumberFormatException e)
		{
			throw new IllegalFileFormatException();
		}
		
		
			inputFile.close();
		
		
			return g;
		
		
	
	}
	
	public static void main(String[] args)
	{
		String filename = "3x3_data.txt";
		String outfile = "3x3_GRAPH_1.txt";
		Graph g;
		
		try
		{
			g = readFile(filename);
			g.calcAdjMatrix();
			g.printGraph(new PrintWriter(new FileOutputStream(outfile)));

		} catch (IllegalFileFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		

	}
}
