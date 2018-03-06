import java.io.*;
import java.util.Scanner; 

public class DoublyLinkedList{
    public static void main(String [] args){
        try{
            Scanner file= new Scanner(new BufferedReader(new FileReader("nums.txt")));
            Digit head= new Digit();
            head.setDigit(file.nextInt());
            Digit curr= head;
            Digit prev= head; 
            while(file.hasNext()){
                curr.setNext(new Digit());
                curr= curr.getNext();
                curr.setDigit(file.nextInt());
                curr.setPrev(prev);
                prev= curr; 
            }
            Digit tail = curr;
            
            
            //to print
            curr= tail;
            while (curr!= null){
                System.out.println(curr.getDigit());
                curr= curr.getPrev();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}