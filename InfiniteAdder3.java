import java.io.*;
import java.util.Scanner;

public class InfiniteAdder3 {
	// start from the tail of a linked list for addition from left to right
	// only positive integers

	// questions: how to print any linked list using a method?
	public static void main(String[] args) {
		try {

			// NOTE: in eclipse, the txt goes in the project folder, not bin or src
			// SCANNING NUM1
			// how to outsource this to a method, to make a new list with head(num)? dynamic
			// variable?
			Scanner scan = new Scanner(new BufferedReader(new FileReader("nums1.txt")));
			Digit head1 = new Digit();
			Digit curr1 = head1;
			Digit prev1 = head1;
			head1.setDigit(scan.nextInt());
			while (scan.hasNextInt()) {
				curr1.setNext(new Digit());
				curr1 = curr1.getNext();
				curr1.setDigit(scan.nextInt());
				curr1.setPrev(prev1);
				prev1 = curr1;
			}
			Digit tail1 = curr1;
			// to print
			curr1 = head1;
			String num1 = "";
			while (curr1 != null) {
				num1 += curr1.getDigit();
				curr1 = curr1.getNext();
			}
			System.out.println(num1);
			curr1= tail1;
			scan.close();
			// SCANNING NUM2
			scan = new Scanner(new BufferedReader(new FileReader("nums2.txt")));
			Digit head2 = new Digit();
			Digit curr2 = head2;
			Digit prev2 = head2;
			head2.setDigit(scan.nextInt());
			while (scan.hasNextInt()) {
				curr2.setNext(new Digit());
				curr2 = curr2.getNext();
				curr2.setDigit(scan.nextInt());
				curr2.setPrev(prev2);
				prev2 = curr2;
			}
			Digit tail2 = curr2;
			// to print
			curr2 = head2;
			String num2 = "";
			while (curr2 != null) {
				num2 += curr2.getDigit();
				curr2 = curr2.getNext();
			}
			System.out.println(num2);
			curr2= tail2;
			scan.close();

			// ADDING
			// if we need to carry a digit when adding
			// we can make a node that stores the 1, and then make the Next
			// point to the next one, and when we add, we include all the nodes
			Digit rtail = new Digit();
			Digit rcurr = rtail;
			Digit rprev = rcurr;
			curr1 = tail1;
			curr2 = tail2;
			// if there is another number in num1 or num2, we continue
			// remember, we are working backwards

			while ((curr2.getPrev() != null) || (curr1.getPrev() != null)) {
				// add the corresponding positions from num1 and num2
				if (curr1.getPrev() == null) {
					rcurr.setDigit(curr2.getDigit());
					rcurr.setPrev(new Digit());
					curr2=curr2.getPrev();
					curr2.setPrev(curr2.getPrev());
				} else if (curr2.getPrev() == null) {
					rcurr.setDigit(curr1.getDigit());
					rcurr.setPrev(new Digit());
					curr1=curr1.getPrev();
					curr1.setPrev(curr1.getPrev());
				} else {
					rcurr.setDigit(curr1.getDigit() + curr2.getDigit());
					rcurr.setPrev(new Digit());
					curr1=curr1.getPrev();
					curr1.setPrev(curr1.getPrev());
					curr2=curr2.getPrev();
					curr2.setPrev(curr2.getPrev());
				}

				// put any carrying into the next Digit
				if (rcurr.getDigit() >= 10) {

					// put a one in rcurr.prev and subtract 10 from rcurr
					rcurr.setDigit(rcurr.getDigit() - 10);

					// PUT A ONE IN RCURR.PREV how?
					rcurr = rcurr.getPrev();
					rcurr.setDigit(rcurr.getDigit() + 1);

				}
				rcurr = rcurr.getPrev();
				rprev= rcurr;
			}
			Digit rhead = rcurr;
			String answer = "";
			while (rcurr != null) {
				answer += rcurr.getDigit();
				rcurr = rcurr.getNext();
			}
			System.out.println(answer);
			scan.close();

		} catch (Exception yikes) {
			System.out.println("Yikes:" + yikes);
			yikes.printStackTrace();
		}

	}

	public static void printList() {

	}
}