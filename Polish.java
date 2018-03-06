import java.util.*;
import java.io.*;


public class Polish {
    public static void main(String[] args) {
        try {
            Scanner file = new Scanner(new BufferedReader(new FileReader("polish.txt")));
            GenericStack<String> operators = new GenericStack<String>();
            GenericStack<Double> operands = new GenericStack<Double>();
            boolean pending_operand = false;
            double operand;
            double operand_1;
            String operator; 

            while(file.hasNext()){
                String token = file.next();

                if(isOperator(token)){
                    operators.push(token);
                    pending_operand = false;
                }else {
                    operand = Double.parseDouble(token);
                    if (pending_operand == true){
                        while(operands.peek()!=null){
                            operand_1 = operands.pop();
                            operator = operators.pop();
                            operand= doTheMath(operand_1, operand, operator);
                        }
                    }
                    operands.push(operand);
                    pending_operand = true;
                }
            }
            // print the result
            System.out.println(operands.pop());

        } catch (Exception ree) {
            System.out.println(ree);
            ree.printStackTrace();
        }
    }

    public static boolean isOperator(String s){
        if(s.equals("+") || s.equals("-")||s.equals("*")|| s.equals("/")){
            return true;
        } else {
            return false;
            }
        
    }
    public static double doTheMath(double a, double b, String s){
        if(s.equals("+")) return a+b;
        if(s.equals("-")) return a-b;
        if(s.equals("*")) return a*b;
        if(s.equals("/")) return a/b;
        return 0;
    }
}
