import java.io.*;
import java.util.Scanner; 

public class InfiniteAdder2{
    public static void main (String [] args){
        try{
            Scanner file1 = new Scanner(new BufferedReader(new FileReader("nums1.txt")));
            Digit head1= new Digit();
            head1.setDigit(file1.nextInt());
            Digit curr1= head1;
            Digit prev1= head1; 
            while(file1.hasNext()){
                curr1.setNext(new Digit());
                curr1= curr1.getNext();
                curr1.setDigit(file1.nextInt());
                curr1.setPrev(prev1);
                prev1= curr1; 
            }
            Digit tail1 = curr1;

            //Number 2
            Scanner file2= new Scanner(new BufferedReader(new FileReader("nums2.txt")));
            Digit head2= new Digit();
            head2.setDigit(file2.nextInt());
            Digit curr2= head2;
            Digit prev2= head2; 
            while(file2.hasNext()){
                curr2.setNext(new Digit());
                curr2= curr2.getNext();
                curr2.setDigit(file2.nextInt());
                curr2.setPrev(prev2);
                prev2= curr2; 
            }
            Digit tail2 = curr2;

            //ADDING
            Digit rtail= new Digit();
            Digit rcurr= rtail;
            //do first one
            rtail.setDigit(tail1.getDigit()+ tail2.getDigit());

            if (rtail.getDigit()>= 10){
                rcurr.setPrev(new Digit());
                rcurr= rcurr.getPrev();
                rcurr.setDigit(1);
                rtail.setDigit( rtail.getDigit()-10);
            }else{
                rcurr.setPrev(new Digit());
                rcurr= rcurr.getPrev();
            }
            curr1= curr1.getPrev();
            curr2= curr2.getPrev();

            while( (curr1 != null)|| (curr2 !=null)){

                //if they both have a previous number
                //for each condition: set rcurr, check if we need to carry, move rcurr to rcurr prev
                if ((curr1!= null)&& (curr2 != null)){
                    rcurr.setDigit( rcurr.getDigit() + curr1.getDigit()+ curr2.getDigit());

                    if (rcurr.getDigit()>= 10){
                        rcurr.setPrev(new Digit());
                        rcurr.setDigit( rtail.getDigit()-10);
                        rcurr= rcurr.getPrev();
                        rcurr.setDigit(1);

                    }else{
                        rcurr.setPrev(new Digit());
                        rcurr= rcurr.getPrev();
                    }
                } else if(curr1 ==null ){
                    //if one of them is null 
                    rcurr.setDigit(rcurr.getDigit() + curr2.getDigit());
                    if (rcurr.getDigit()>= 10){
                        rcurr.setPrev(new Digit());
                        rcurr.setDigit( rtail.getDigit()-10);
                        rcurr= rcurr.getPrev();
                        rcurr.setDigit(1);

                    }else{
                        rcurr.setPrev(new Digit());
                        rcurr= rcurr.getPrev();
                    }

                } else if (curr2.getPrev()==null){
                    rcurr.setDigit(rcurr.getDigit() + curr1.getDigit());
                    if (rcurr.getDigit()>= 10){
                        rcurr.setPrev(new Digit());
                        rcurr.setDigit( rtail.getDigit()-10);
                        rcurr= rcurr.getPrev();
                        rcurr.setDigit(1);

                    }else{
                        rcurr.setPrev(new Digit());
                        rcurr= rcurr.getPrev();
                    }
                }

                if( curr1 != null && curr2 == null){
                    curr1= curr1.getPrev();
                } else if( curr2 == null && curr2!=null){
                    curr2= curr2.getPrev();
                } else {
                    curr1= curr1.getPrev();
                    curr2= curr2.getPrev();
                }
            }

            //printing the result
            rcurr= rtail;
            while (rcurr!= null){
                System.out.println(rcurr.getDigit());
                rcurr= rcurr.getPrev();
            } 
        }   
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
