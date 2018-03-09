

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
            //note: all positions are stored as an array
            // [x,y]

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
            String[][] mazeArray = new String[rows][columns];
            for(int r=0;r<rows;r++){
                String row = mazeFile.nextLine();
                for(int c=0; c<columns;c++){
                    mazeArray[r][c]= row.substring(c,c+1);
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

            /* The algorithm:
            *  Find start
            *  Push position on Stack
            *  Are you done? (You are on the finish spot), then stack has solution
            *  If you can Go Up without backtracking, go to #2
            *  If you can Go Down without backtracking, go to #2
            *  If you can Go Left without backtracking, go to #2
            *  If you can Go Right without backtracking, go to #2
            *  Pop Position off Stack, go to #2 (if empty then maze is impossible)
            */

            //find start
            int [] startPos= new int[2];
            for(int r=0;r<rows;r++){
                for(int c=0;c<columns;c++){
                    if(mazeArray[r][c].equals("@")){
                        startPos[0]=r;
                        startPos[1]=c;
                        break;
                    }
                }
            }

            //push position to stack
            GenericStack<int []> positionStack = new GenericStack<int []>();
            positionStack.push(startPos);



            //???start tracking all of our past positions
            //maybe use ArrayList?
            //int[][] pastPos= new

            //the big stuff
            //in here we will keep moving our position until we determine
            //the maze to be impossible
            boolean isPossible=true;
            while(!(isDone(mazeArray, positionStack.peek()))){
                //use continue statement


                if(positionStack.peek()==null){
                    isPossible=false;
                }
            }

            if(!isPossible){
                System.out.println("That maze isn't possible");
            }

        } catch (Exception oof) {
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }
    }


    public static boolean isDone (String[][] maze, int [] position){
        int rows = position[0];
        int columns = position[1];
        if(maze[rows][columns].equals("$")){
            return true;
        } else{
            return false;
        }
    }

    /**
    * Determines if we can move in the given direction and not backtrack
    * Still need to figure out how to store past positions effectively
    */
    public static boolean canMove (String[][] maze, int [] position, int [][] pastPos, String movement){
        if(movement.equals("up")){

        }

        return false;
    }
}
