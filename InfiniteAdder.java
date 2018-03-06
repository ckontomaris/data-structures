/**
 * The program imports two numbers from files num1.txt and num2.txt
 * The numbers are stored as Doubly Linked Lists and added into a new Linked List.
 * The program prints the resulting Linked List and displays a message.
 * @author Chris Kontomaris
 * @version 2/13/18
 */

import java.io.*;
import java.util.Scanner;

public class InfiniteAdder {
    public static void main(String[] args) {
        try {
            // import num1
            Scanner f1 = new Scanner(new BufferedReader(new FileReader("nums1.txt")));
            Digit head1 = new Digit();
            Digit curr1 = head1;
            Digit prev1 = head1;

            head1.setDigit(f1.nextInt());
            while (f1.hasNext()) {
                curr1.setNext(new Digit());
                curr1 = curr1.getNext();
                curr1.setDigit(f1.nextInt());
                curr1.setPrev(prev1);
                prev1 = curr1;
            }
            Digit tail1 = curr1;

            f1.close();
            // import num2
            Scanner f2 = new Scanner(new BufferedReader(new FileReader("nums2.txt")));
            Digit head2 = new Digit();
            Digit curr2 = head2;
            Digit prev2 = head2;
            head2.setDigit(f2.nextInt());
            while (f2.hasNext()) {
                curr2.setNext(new Digit());
                curr2 = curr2.getNext();
                curr2.setDigit(f2.nextInt());
                curr2.setPrev(prev2);
                prev2 = curr2;
            }
            Digit tail2 = curr2;
            f2.close();

            // ADDING

            /*
             * We are going to make a new list and work backwards to the front As long as
             * any of the 2 numbers have more to go, take the number and put it in the
             * "result" list
             * if the added number is greater than 10, we can carry by making
             * the "prev" number bigger by 1
             */
            curr1 = tail1;
            curr2 = tail2;
            Digit rtail = new Digit();
            Digit rcurr = rtail;
            Digit rnext = rtail;
            // do the first input manually, for the same reasons as importing f1 and f2
            rcurr.setDigit(curr1.getDigit() + curr2.getDigit());
            curr1 = curr1.getPrev();
            curr2 = curr2.getPrev();

            while (curr1 != null || curr2 != null) {
                // decide whether the new slot needs to have a carrying 1
                if (rcurr.getDigit() >= 10) {
                    rcurr.setPrev(new Digit());
                    rcurr.setDigit(rcurr.getDigit() - 10);
                    rcurr = rcurr.getPrev();
                    rcurr.setNext(rnext);
                    rnext = rcurr;
                    rcurr.setDigit(1);
                } else {
                    // if we don't need to carry, just make an empty prev slot
                    rcurr.setPrev(new Digit());
                    rcurr = rcurr.getPrev();
                    rcurr.setNext(rnext);
                    rnext = rcurr;
                }
                // now add whatever is not null
                if (curr1 != null && curr2 != null) {
                    // don't forget to have rcurr.getDigit in here bc then it won't carry
                    rcurr.setDigit(rcurr.getDigit() + curr1.getDigit() + curr2.getDigit());
                    curr1 = curr1.getPrev();
                    curr2 = curr2.getPrev();
                } else if (curr1 == null && curr2 != null) {
                    rcurr.setDigit(rcurr.getDigit() + curr2.getDigit());
                    curr2 = curr2.getPrev();
                } else if (curr1 != null && curr2 == null) {
                    rcurr.setDigit(rcurr.getDigit() + curr1.getDigit());
                    curr1 = curr1.getPrev();
                }
                // we don't need to do the check at the end of the loop
                // because if we end with the rhead containing a 10 or above
                // when we print it it will still be correct

            }
            Digit rhead = rcurr;

            // Print the whole number sentence

            // print num1
            curr1 = head1;
            while (curr1 != null) {
                System.out.print(curr1.getDigit());
                curr1 = curr1.getNext();

            }
            System.out.print(" + ");

            // print num2
            curr2 = head2;
            while (curr2 != null) {
                System.out.print(curr2.getDigit());
                curr2 = curr2.getNext();
            }
            System.out.print(" = ");
            // print the result
            rcurr = rhead; // this is to remind me that rcurr is at the front
            while (rcurr != null) {
                System.out.print(rcurr.getDigit());
                rcurr = rcurr.getNext();
            }
            System.out.println();
            System.out.println("Thanks for using my calculator!");

        } catch (Exception nope) {
            System.out.println("Something went wrong: " + nope);
            nope.printStackTrace();
        }
    }
}
