import java.util.*;
import java.io.*;

public class MazeSolver {
    public static void main(String[] args) {
        try {
            Scanner mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));

            int rows = 0;
            int columns = 0;

            //columns = number of characters in a row
            //rows = # of times we can NextLine
            while (mazeFile.hasNextLine()) {
                mazeFile.nextLine();
                rows++;
            }
            mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));
            String firstRow = mazeFile.nextLine();
            //the number of columns in the first row
            columns = firstRow.length();

            mazeFile = new Scanner(new BufferedReader(new FileReader("maze.txt")));
            //make an array with the maze
            String[][] maze = new String[rows][columns];
            for (int r = 0; r < rows; r++) {
                String row = mazeFile.nextLine();
                for (int c = 0; c < columns; c++) {
                    maze[r][c] = row.substring(c, c + 1);
                }
            }

            //PRINTING THE MAZE FOR FUN
            System.out.println("So this is what I'm solving, eh?");
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    System.out.print(maze[r][c]);
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
            int[] start = new int[2];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    if (maze[r][c].equals("@")) {
                        start[0] = r;
                        start[1] = c;
                        break;
                    }
                }
            }

            //remember that [row,column] is how position is stored

            //make a stack of positions
            GenericStack<int[]> positionStack = new GenericStack<int[]>();
            ArrayList<int[]> positionList = new ArrayList<int[]>();
            int[] position = start;
            positionStack.push(position);
            System.out.println("Here we go!");
            
            
            while (!done(maze, position)) {

                //go until you can't anymore
                //we need a way to prevent it from going up and then down forever
                //ARRAYS ARE PASSBY REFERENCE
                while(canMove(maze, position, "any")){
                    
                    if(canMove(maze, position, "up") && (!alContainsObject(positionList, move(maze, position, "up")))){
                        position = move(maze, position, "up");
                        positionStack.push(position);
                        positionList.add(position);
                    } else if (canMove(maze, position, "down") && (!alContainsObject(positionList, move(maze, position, "down")))){
                        position = move(maze, position, "down");
                        positionStack.push(position);
                        positionList.add(position);
                    } else if (canMove(maze, position, "left") && (!alContainsObject(positionList, move(maze, position, "left")))){
                        position = move(maze, position, "left");
                        positionStack.push(position);
                        positionList.add(position);
                    } else if (canMove(maze, position, "right") && (!alContainsObject(positionList, move(maze, position, "right")))){
                        position = move(maze, position, "right");
                        positionStack.push(position);
                        positionList.add(position);
                    } else {
                        break;
                    }
                }
                //if you still aren't done, backtrack and put a wall where you were
                //by doing this, you slowly close off your "false leads"
                if(!done(maze, position)){
                    int r = position[0];
                    int c = position[1];
                    maze[r][c]= "#";
                    //make our position into a wall

                    //move to our last position
                    positionStack.pop();
                    position = positionStack.peek();
                    //positionStack.pop();
                }
            }
            //this method does destroy the positionStack 
            printStackReverse(positionStack);
            
        } catch (Exception oof) {
            System.out.println(oof.getMessage());
            oof.printStackTrace();
        }

    }

    public static boolean done(String[][] maze, int[] position) {
        int row = position[0];
        int column = position[1];
        if (maze[row][column].equals("$")) {
            return true;
        } else {
            return false;
        }
    }

    public static void printPosition(int[] currPos) {
        System.out.println(currPos[0] + "," + currPos[1]);
    }

    public static boolean canMove(String[][] maze, int[] position, String direction) {
        int row = position[0]; //these are 0 indexed
        int col = position[1]; //top left is 0,0
        //going down will be adding

        //we need to see if it will go out of bounds, or hit a wall
        //remember that .length is NOT 0 indexed
        if (direction.equals("up")) {
            if ((row - 1) < 0) {
                return false;
            } else if (maze[row - 1][col].equals("#")) {
                return false;
            } else {
                return true;
            }
        } else if (direction.equals("down")) {
            if ((row + 1) > (maze.length - 1)) {
                return false;
            } else if (maze[row + 1][col].equals("#")) {
                return false;
            } else {
                return true;
            }
        } else if (direction.equals("left")) {
            if ((col - 1) < 0) {
                return false;
            } else if (maze[row][col - 1].equals("#")) {
                return false;
            } else {
                return true;
            }
        } else if (direction.equals("right")) {
            if ((col + 1) > (maze[0].length - 1)) {
                return false;
            } else if (maze[row][col + 1].equals("#")) {
                return false;
            } else {
                return true;
            }
        } else if (direction.equals("any")){
            boolean up = canMove(maze, position, "up");
            boolean down = canMove(maze, position, "down");
            boolean left = canMove(maze, position, "left");
            boolean right = canMove(maze, position, "right");
            if (up || down || left || right){
                return true;
            }
        }
        return true;

    }

    public static int[] move(String[][] maze, int[] position, String direction) {
        //arrays are pass-by objects
        //this method is used when I don't actually want to change position for real
        //so i need to make a local position array to use and return
        int[] localPos = new int[2];
        localPos[0] = position[0];
        localPos[1]= position[1];
        
        
        int row = localPos[0];
        int col = localPos[1];

        if (direction.equals("up")) {
            localPos[0] = localPos[0] - 1;
            return localPos;
        } else if (direction.equals("down")) {
            localPos[0] = localPos[0] + 1;
            return localPos;
        } else if (direction.equals("left")) {
            localPos[1] = localPos[1] - 1;
            return localPos;
        } else if (direction.equals("right")) {
            localPos[1] = localPos[1] + 1;
            return localPos;
        } else {
            return localPos;
        }

    }
    public static boolean alContainsObject(ArrayList<int []> list, int[] object){
        //i need a way to check if the ArrayList of positions includes a potential future position
        //array list doesn't compare objects properly
        int size = list.size();
        
        for(int x=0; x<list.size(); x++){
            if (Arrays.equals(list.get(x), object) ){
                return true;
            } else{
            }
        }
        
        
        return false; 
    }
    public static void printStackReverse(GenericStack<int []> orig){
        //just pop the stack into another stack, that will reverse the order
        GenericStack<int []> rev = new GenericStack<int []>();
        while(orig.peek() != null){
            rev.push(orig.pop());
        }
        //then print
        while(rev.peek()!=null){
            printPosition(rev.pop());
        }
    }
}
