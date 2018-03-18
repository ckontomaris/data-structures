

public class ArrayPractice{
    public static void main(String [] args){
    
        int[][] rand = new int[2][7];
        
        
        System.out.println(rand.length); //prints  2
        System.out.println(rand[0].length); //prints 7
        System.out.println(rand[2][7]); //array is out of bounds
    }
}