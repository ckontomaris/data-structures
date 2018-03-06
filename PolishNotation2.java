import java.util.*;
import java.io.*;


/**
 * Imports an expression in polish notation and prints the result.
 * 
 * @author Chris Kontomaris
 * @version 2/28/18
 *
 */



public class PolishNotation2 {
	public static void main(String[] args) {
		try {
			Scanner theFile = new Scanner(new BufferedReader(new FileReader("polish.txt")));
			GenericStack<String> file = new GenericStack<String>();
			GenericStack<Double> resultant= new GenericStack<Double>();

			while (theFile.hasNext()) {
				file.push(theFile.next());
			}

			// stuff we need
			double operand1;
			double operand2;
			// our current token
			String token;

			

		} catch (Exception ree) {
			System.out.println(ree);
			ree.printStackTrace();
		}
	}
}
