

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
            //the number of rows and columns cant be different right?

            // iterate through the file to determine rows and columns
            int rows= 0;
            int columns= 0;

            //to determine # of columns, we need to see how many times we can .next() before changing lines
            //to determine # of rows, we need to see how many times we go to the next line
            //this loops through the possible number of lines
            while(mazeFile.hasNextLine()) {
                mazeFile.nextLine();
                rows++;
            }
            //resets the file

            mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));

            String firstRow= mazeFile.nextLine();
            //the number of columns in the first row
            columns = firstRow.length();

            System.out.println(rows);
            System.out.println(columns);
            mazeFile.close();
            //make an array with the same dimensions as the file
            //and reset the file again
            Scanner arrayFiller = new Scanner(new BufferedReader(new FileReader("maze.txt")));
            //this is nessecary to have the scanner.next() only return a character
            //without this it returns the whole line idk why
            arrayFiller.useDelimiter("");
            String[][] mazeArray = new String[rows][columns];

            for(int r = 0; r<rows; r++){
                for(int c= 0; c<columns;c++){
                    //why would it just stop in the last row at the 6th col???
                    mazeArray[r][c]= arrayFiller.next();
                }
            }
            //print the maze for fun
            for(int r=0; r<rows;r++){
                for(int c=0; c<columns;c++){
                    System.out.print(mazeArray[r][c]);
                }
            }
            arrayFiller.close();


        } catch (Exception oof) {
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }
    }
}
