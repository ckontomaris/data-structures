import java.util.*;
import java.io.*;


/**
 * Imports an expression in polish notation and prints the result.
 * 
 * @author Chris Kontomaris
 * @version 2/28/18
 *
 */



public class PolishNotation1 {
	public static void main(String[] args) {
		try {
			Scanner theFile = new Scanner(new BufferedReader(new FileReader("polish.txt")));
			GenericStack<String> file = new GenericStack<String>();
			GenericStack<Integer> resultant= new GenericStack<Integer>();

			while (theFile.hasNext()) {
				file.push(theFile.next());
			}

			// stuff we need
			double operand1;
			double operand2;
			// our current token
			String token;

			while (file.peek() != null) {
				token = file.pop();
				
				// if token is an operator
				if (token == "+" || token == "-" || token == "*" || token == "/") {
					//get the next two numbers
					operand1 = Double.parseDouble(file.pop());
					operand2 = Double.parseDouble(file.pop());
					// do the operation and push to the stack
					if (token == "+") {
						double result = operand1 + operand2;
						file.push(Double.toString(result));
					} else if (token == "-") {
						double result = operand1 - operand2;
						file.push(Double.toString(result));
					} else if (token == "*") {
						double result = operand1 * operand2;
						file.push(Double.toString(result));
					} else if (token == "/") {
						double result = operand1 / operand2;
						file.push(Double.toString(result));
					}
				} else {
					file.push(token);
				}

			}
			// print the result
			System.out.println(file.pop());

		} catch (Exception ree) {
			System.out.println(ree);
			ree.printStackTrace();
		}
	}
}
