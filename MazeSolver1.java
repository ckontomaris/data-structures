import java.io.*;
import java.util.*;

public class MazeSolver1{
    public static void main(String [] args){
        try {
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
            printPosition(currPos);

            //use array list to make it easy to see if we backtracked (contains method)
            ArrayList<int []> pastPos = new ArrayList<int []>();
            //pastPos.add(currPos);

            while(!isDone(mazeArray,currPos)){
                //go up until you cant any more, then go left
                //once u can;t move anywhere, backup to last decision and change direction
                System.out.print("h");
                while (canMove(mazeArray, currPos, "any")){
                    System.out.print("v");
                    if (canMove(mazeArray, currPos, "up")&&(!pastPos.contains(currPos))){
                        currPos = Move(mazeArray, currPos, "up");
                        positionStack.push(currPos);
                        pastPos.add(currPos);
                    } else if (canMove(mazeArray, currPos, "down")&&(!pastPos.contains(currPos))){
                        currPos = Move(mazeArray, currPos, "down");
                        positionStack.push(currPos);
                        pastPos.add(currPos);
                    } else if (canMove(mazeArray, currPos, "left")&&(!pastPos.contains(currPos))){
                        currPos = Move(mazeArray, currPos, "left");
                        positionStack.push(currPos);
                        pastPos.add(currPos);
                    } else if (canMove(mazeArray, currPos, "right")&&(!pastPos.contains(currPos))){
                        currPos = Move(mazeArray, currPos, "right");
                        positionStack.push(currPos);
                        pastPos.add(currPos);
                    } else {
                        //backtrack
                        break;
                        //used to be a currPos = positionStack.pop();
                    }

                }
                //if we can't move any other direction, we need to backup
                currPos = positionStack.pop();
            }
        } catch (Exception oof){
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }
    }

    public static void printPosition (int[] currPos){
        System.out.println(currPos[0]+ ","+ currPos[1]);
    }

    public static boolean isDone(String[][] maze, int[] position){
        int row = position[0];
        int column = position[1];
        if (maze[row][column].equals("$")){
            return true;
        } else {
            return false;
        }
    }

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

    public static boolean canMove (String[][] maze, int [] position,  String movement){
        //x,y
        int x = position[0];
        int y = position[1];
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
        } else if(movement.equals("any")) {
            boolean up = canMove(maze, position, "up");
            boolean down = canMove(maze, position, "down");
            boolean left = canMove(maze, position, "left");
            boolean right = canMove(maze, position, "right");
            if(up || down || left || right){
                return true;
            } else {
                return false;
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
