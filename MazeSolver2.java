import java.util.*;
import java.io.*;

public class MazeSolver2 {
    public static void main(String [] args){
        try{

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
            int [] start= new int[2];
            for(int r=0;r<rows;r++){
                for(int c=0;c<columns;c++){
                    if(mazeArray[r][c].equals("@")){
                        start[0]=r;
                        start[1]=c;
                        break;
                    }
                }
            }

            //push start to stack
            GenericStack<int []> positionStack = new GenericStack<int []>();
            positionStack.push(start);
            //use an array list to use .contains
            ArrayList<int []> positionList = new ArrayList<int []>();
            //positionList.add(start);

            //as long as we aren't done..
            //remember we are doing [x,y] just like coordinate system
            while(!done(mazeArray, positionStack.peek())){
                printPosition(positionStack.peek());
                if(canMove(mazeArray, positionStack.peek(), "up")&&(!positionList.contains(positionStack.peek()))){
                    int [] currPos = positionStack.peek();
                    currPos[1]= currPos[1] +1;
                    positionStack.push(currPos);
                    positionList.add(currPos);
                } else if(canMove(mazeArray, positionStack.peek(), "down")&&(!positionList.contains(positionStack.peek()))){
                    int [] currPos = positionStack.peek();
                    currPos[1]= currPos[1] -1;
                    positionStack.push(currPos);
                    positionList.add(currPos);
                } else if(canMove(mazeArray, positionStack.peek(), "left")&&(!positionList.contains(positionStack.peek()))){
                    int [] currPos = positionStack.peek();
                    currPos[0]= currPos[0] -1;
                    positionStack.push(currPos);
                    positionList.add(currPos);
                } else if(canMove(mazeArray, positionStack.peek(), "right")&&(!positionList.contains(positionStack.peek()))){
                    int [] currPos = positionStack.peek();
                    currPos[0]= currPos[0] +1;
                    positionStack.push(currPos);
                    positionList.add(currPos);

                } else {
                    //pop position off Stack and recheck if we are done
                    positionStack.pop();
                    if (positionStack.peek()==null){
                        System.out.println("This is impossible!");
                        break;
                    }
                }


            }
        } catch (Exception oof){
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }
    }

    public static boolean done (String[][] maze, int [] position){
        int row=position[0];
        int column= position[1];
        if (maze[row][column].equals("$")){
            return true;
        } else {
            return false;
        }
    }
    public static void printPosition(int [] currPos){
        System.out.println(currPos[0]+ ","+ currPos[1]);
    }
    public static boolean canMove(String[][] maze, int[] position, String movement){
        int x = position[0];
        int y = position[1];

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

}
