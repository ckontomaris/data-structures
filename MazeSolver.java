
/**
 * Uses a stack of positions to solve a maze
 * 
 * @author Chris Kontomaris
 * @version	3/3/18
 */

import java.io.*;
import java.util.*;

public class MazeSolver {
	public static void main(String[] args) {
		try {
			Scanner mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));
			//the number of rows and columns cant be different right?
			
			
			// iterate through the file to determine rows and columns
			int rows= 0;
			int columns= 0;
			
		    
			//to determine # of columns, we need to see how many times we can .next() before changing lines
			
			while(mazeFile.hasNextLine()) {
				mazeFile.nextLine();
				rows++;
			}
			mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));
			String firstRow= mazeFile.nextLine();
			System.out.println(firstRow);
			
			
			System.out.println(rows);
			System.out.println(columns);
			//to determine # of rows, we need to see how many times we go to the next line
			
			// String[][] maze = new String[x][y]; we also subtract 1 

		} catch (Exception oof) {
			System.out.println(oof.getMessage());
			oof.printStackTrace();
		}
	}
}
