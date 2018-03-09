

import java.io.*;
import java.util.*;
/**
* Uses a stack of positions to solve a maze
*
* @author Chris Kontomaris
* @version    3/3/18
*/

public class MazeSolver {
    public static void main(String[] args) {
        try {
            Scanner mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));

            // we need to determine rows and columns to make an array of the maze
            int rows= 0;
            int columns= 0;

            //to determine # of columns, we need to see how many times we can .next() before changing lines
            //to determine # of rows, we need to see how many times we go to the next line
            while(mazeFile.hasNextLine()) {
                mazeFile.nextLine();
                rows++;
            }

            //resets the file
            mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));

            String firstRow= mazeFile.nextLine();
            //the number of columns in the first row
            columns = firstRow.length();

            //now we can use the String.charat method to take each char in the file
            mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));
            //make an array
            char[][] mazeArray = new char[rows][columns];
            for(int r=0;r<rows;r++){
                String row = mazeFile.nextLine();
                for(int c=0; c<columns;c++){
                    mazeArray[r][c]= row.charAt(c);
                }
            }

            //print the maze for fun
            System.out.println("So this is what I'm solving, eh?");
            for(int r=0; r<rows;r++){
                for(int c=0; c<columns;c++){
                    System.out.print(mazeArray[r][c]);
                }
                System.out.println("");
            }



        } catch (Exception oof) {
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }
    }
}
