import java.util.*;

public class ArrayListPractice{
    public static void main(String [] args){
        ArrayList<int []> ayy = new ArrayList<int []>();
        int [] rand = {12,32};
        int [] rand1 = {4, 45};
        ayy.add(rand);
        ayy.add(rand1);
        
        // # of elements
        System.out.println(ayy.size());
        //indexed from zero
        int[] get = ayy.get(0);
        System.out.println(get[0]);
        
        
        //looking for a certain object 

        int[] object = {12 , 32};
        for(int x=0; x<ayy.size(); x++){
            if (Arrays.equals(ayy.get(x), object) ){
                System.out.println("true");
            } else{
                
            }
        }
        
    }
}