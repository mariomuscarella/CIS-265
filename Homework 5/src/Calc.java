//*******************************************
// CIS 265 Fall, 2015 ‐  Section 50     
// Homework #5 – infix postfix
// NAME: Mario Muscarella                      
// CSU ID: 2478702       
// DATE & TIME: 11/2 3PM           
//**************************************

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Calc {

    public static void main(String[] args) throws IOException
    {
        String expression;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter expression: ");
        expression = br.readLine();

        Converter pc = new Converter(expression); 
        System.out.print("Postfix: "); 
        pc.printExpression();

        PostFix calc = new PostFix(pc.getPostfixAsList());
        System.out.println();
        System.out.println("Result: " + calc.result());
    }
}
