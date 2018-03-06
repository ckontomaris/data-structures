import java.io.*;
import java.util.Scanner; 

public class InfiniteAdder1{
    public static void main(String [] args){
        try{
            //import the first number
            Scanner file1= new Scanner(new BufferedReader(new FileReader("nums1.txt")));
            Digit head1= new Digit();
            Digit curr1= head1;
            Digit prev1= head1;
            head1.setDigit(file1.nextInt());
            while(file1.hasNext()){
                curr1.setNext(new Digit());
                curr1= curr1.getNext();
                curr1.setDigit(file1.nextInt());
                curr1.setPrev(prev1);
                prev1= curr1; 
            }
            Digit tail1= curr1;

            //print out
            curr1= head1;
            while(curr1!= null){
                System.out.print(curr1.getDigit());
                curr1= curr1.getNext();
            }
            System.out.println(" ");

            Scanner file2= new Scanner(new BufferedReader(new FileReader("nums2.txt")));
            Digit head2= new Digit();
            Digit curr2= head2;
            Digit prev2= head2;
            head2.setDigit(file2.nextInt());

            while(file2.hasNext()){
                curr2.setNext(new Digit());
                curr2= curr2.getNext();
                curr2.setDigit(file2.nextInt());
                curr2.setPrev(prev2);
                prev2= curr2;
            }
            Digit tail2= curr2;
            //printing num2
            curr2= head2;
            while(curr2!=null){
                //print, move to the next
                System.out.print(curr2.getDigit());
                curr2= curr2.getNext();
            }

            //curr1 is at the tail
            //curr2 is at the tail
            //ADDING INTO A NEW LIST
            //make a new "result" list 
            Digit rtail= new Digit();
            Digit rcurr= rtail;
            Digit rprev= rtail;
            rtail.setDigit(curr1.getDigit()+curr2.getDigit());
            //we are going to start at the end of each imported number

            //remember than when we import the numbers, we need to do the first one 
            //outside of the loop (bc otherwise we can't have a condition for the loop)
            //^^ basically, we don't want to automatically start making a new next node if there isn't a value
            //when the loop starts after one digit has already been filled AND there is file.nextInt,
            //then we know it was ok to make a new digit
            //as long as either list has another previous number, we can continue
            while ( (curr2 != null) || (curr1!=null)){

                if (rcurr.getDigit() >= 10){
                //if it was more then 10, initialize our next digit to be a 1 (to carry)
                }
                else{
                    //otherwise, make our next node be empty
                    rcurr= rcurr.getNext();
                }
                
                //we also need conditions to check if we are at the end of either list
                //if we are, use the last valyue and make that curr into a null
                //if its null from the last iteration, you know we already used the last value in the list
                //make sure we get have good conditons so that we don't add a null or getNext a null
            }

        }
        catch (Exception crap){
            System.out.println("Oh no:" +crap);
            crap.printStackTrace();
        }
    }
}