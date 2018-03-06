
/**
 * This program reads a text file called pchecker.txt 
 * Scans the file for { and } and pushes a String onto a stack depending on the character
 * Uses the stack to determine if the file an unequal number of { or }
 * 
 * @author Chris Kontomaris
 * @version 2/24/18
 */

import java.io.*;
import java.util.Scanner;

public class ParenthesisCheck {
	public static void main(String[] args) {
		try {

			Scanner file = new Scanner(new BufferedReader(new FileReader(("pchecker.txt"))));
			// import the file to a string
			String codeLine = file.nextLine();

			// make a stack
			GenericStack<String> bigStacks = new GenericStack<String>();

			String holder;
			// forgot to use .equals and used == and it didn't work

			// push a string on when we see a {
			// pop a string off when we see a }
			for (int i = 0; i < codeLine.length(); i++) {
				holder = codeLine.substring(i, i + 1);
				if (holder.equals("{")) {
					bigStacks.push("Anotha one");
				} else if (holder.equals("}")) {
				    if (bigStacks.pop() == null){
					    System.out.println("Too many");
					   }
				}

			}

			// if its null at the end, we added a removed an equal number of { and }
			// otherwise, there was too many { or }
			if (bigStacks.peek() == null) {
				System.out.println("Perfect!");
			} else {
				System.out.println("something's wrong: too many { or }");
			}

			file.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}