/** This program uses an intermediate Linked List in order to print a list of numbers
from a file.
 * @author Chris Kontomaris
 * @version 1/31/2018
 */
import java.io.*;
import java.util.Scanner;
public class NodeFun
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
            Node head= new Node(0);
            Node curr= new Node(0);
            curr=head;
            //for each
            while (scan.hasNext()){
                //set a temp variable to be the next number in the file
                int fileTemp= scan.nextInt();
                //set curr to the number, and move curr into a new node
                curr.set(fileTemp);
                curr.setNext(new Node(0));
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
