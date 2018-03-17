import java.io.*;
import java.util.*;
/**
* Uses a stack of positions to solve a maze
*
* @author Chris Kontomaris
* @version    3/16/18
*/

public class MazeSolver {
    public static void main(String[] args) {
        try {
            //note: all positions are stored as an array
            // [x,y]

            Scanner mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));

            //IMPORTING FILE
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

            //PRINTING THE MAZE FOR FUN
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

            //FINDING THE START
            int [] currPos= new int[2];
            for(int r=0;r<rows;r++){
                for(int c=0;c<columns;c++){
                    if(mazeArray[r][c].equals("@")){
                        currPos[0]=r;
                        currPos[1]=c;
                        break;
                    }
                }
            }

            //push position to stack
            //this stack contains all the past positions
            GenericStack<int []> positionStack = new GenericStack<int []>();
            positionStack.push(currPos);

            //the big stuff
            //in here we will keep moving our position until we determine
            //the maze to be impossible
            boolean isPossible=true;

            while(!(isDone(mazeArray, positionStack.peek()))){
                //we need a way to move until we can't anymore, and then backtrack
                if (canMove(mazeArray, currPos, "up")){
                    currPos = moveUntilWall(mazeArray,currPos, "up");
                    positionStack.push(currPos);
                } else if (canMove(mazeArray, currPos, "down")){
                    currPos = moveUntilWall(mazeArray,currPos, "down");
                    positionStack.push(currPos);
                } else if (canMove(mazeArray, currPos, "left")){
                    currPos = moveUntilWall(mazeArray,currPos, "left");
                    positionStack.push(currPos);
                } else if (canMove(mazeArray, currPos, "right")){
                    currPos = moveUntilWall(mazeArray,currPos, "right");
                    positionStack.push(currPos);
                }
                System.out.println(currPos[0]);
                System.out.println(currPos[1]);

            }

            if(!isPossible){
                System.out.println("That maze isn't possible");


            }
        }catch (Exception oof) {
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }
    }

    public static boolean isDone (String[][] maze, int [] position){
        int rows = position[0];
        int columns = position[1];
        if(maze[rows][columns].equals("$")){
            return true;
        } else {
            return false;
        }
    }

    /**
    * Returns an position array that moved in the given direction and not backtrack
    * Still need to figure out how to store past positions effectively (use a linked list of pos []s and cycle through)
    */
    public static int[] Move (String[][] maze, int [] position, String movement){
        //x,y
        //changed return type to int[], idk what i was  using this for before
        if(movement.equals("up")){
            position[1] = position[1] +1;
            return position;
        } else if( movement.equals("down")){
            position[1] = position[1] -1;
            return position;
        } else if( movement.equals("left")){
            position[0]= position[0]-1;
            return position;
        } else if( movement.equals("right")){
            position[0] = position[0]+1;
            return position;
        }
        System.out.println("I wasn't passed a direction");
        return position;
    }

    /**
    * Determines if we can move in the given direction and not hit a wall (or backtrack?)
    * Still need to figure out how to store past positions effectively (use a linked list of pos []s and cycle through)
    */
    public static boolean canMove (String[][] maze, int [] position,  String movement){
        //x,y
        int r = position[0];
        int c = position[1];
        int x = r;
        int y = c;
        //changed return type to int[], idk what i was  using this for before

        if(movement.equals("up")){
            if((y+1)>maze.length){
                return false;
            } else if(maze[x][y+1].equals("#")){
                return false;
            } else {
                return true;
            }
        } else if( movement.equals("down")){
            if((y-1)<0){
                return false;
            }
            else if(maze[x][y-1].equals("#")){
                return false;
            } else {
                return true;
            }
        } else if( movement.equals("left")){
            if((x-1)<0){
                return false;
            }
            else if(maze[x-1][y].equals("#")){
                return false;
            } else {
                return true;
            }
        } else if( movement.equals("right")){
            if((x+1)>maze[0].length){
                return false;
            }
            else if(maze[x+1][y].equals("#")){
                return false;
            } else {
                return true;
            }
        }

        return false;
    }
    public static int[] moveUntilWall(String[][] maze, int[] position, String direction){
        while(canMove(maze, position, direction)){
            position = Move(maze, position, direction);
            if (isDone(maze, position)){
                return position;
            }
        }
        return position;

    }
}
