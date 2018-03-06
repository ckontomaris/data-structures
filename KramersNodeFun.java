/** This program uses an intermediate Linked List in order to print a list of numbers
from a file.
 * @author Chris Kontomaris
 * @version 1/31/2018
 */
import java.io.*;
import java.util.Scanner;
public class KramersNodeFun
{
    /**
     * Reads nums.txt, and imports each new number into an unsorted Linked List.
     * Traverses the linked list from the beginning and prints each element.
     * @param passes command line arguments array into the program
     * @return prints the numbers contained in nums.txt
     */
    public static void main(String [] args) {
        try{
            //open a scanner to nums.txt
            Scanner scan = new Scanner(new BufferedReader(new FileReader("nums.txt")));
            //start a new Linked List
            Node head= new Node(scan.nextInt());
            Node curr=head; 
            while(scan.hasNext()){
                curr.setNext(new Node(scan.nextInt()));
                curr=curr.getNext();
            }

            //printing
            //set our "counting" variable to the beginning of the Linked List
            curr= head;
            //print the value of the node, follow the pointer
            //repeat until no nodes are left
            while(curr != null) {
                System.out.println(curr.get());
                curr = curr.getNext();
            }
        }
        catch (Exception nope){
            System.out.println("Something went wrong: " + nope.getMessage());
            //gives more detail about exception (like line number)
            nope.printStackTrace();
        }
    }
}
