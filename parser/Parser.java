package parser;
import scanner.Scanner;
import java.io.*;
import java.util.*;

/**
 * Write a description of class Parser here.
 * 
 * @author Rahul Mehta
 * @version 
 */
public class Parser
{
    public Scanner scanner;
    String currentToken;
    /**
     * Constructor for objects of class Parser
     */
    public Parser(Scanner scannertouse)
    {
        scanner = scannertouse;
        try 
        {
            currentToken = scannertouse.nextToken(); 
        }
        catch (Exception e)
        {
            System.out.println("Hi, you have a scanErrorException");
            System.exit(1);
        }

    }

    public void eat(String expectedtoken) throws IllegalArgumentException
    {
        if (currentToken.equals(expectedtoken))
        {
            try
            {
                currentToken = scanner.nextToken();
            }
            catch (Exception e)
            {
                System.out.println("Hi, you have a scanErrorException");
            }
        }
        else
        {
            throw new IllegalArgumentException (expectedtoken + "expected, but found " + currentToken);
        }
    }

    private int parseNumber()
    {
        
        eat(currentToken);
        return num;
    }

    public int parseFactor()
    {
        if (currentToken.equals("-"))
        {
            eat("-");
            return (-1 * parseFactor());
        }
        else if (currentToken.equals("("))
        {
            int value;
            eat("(");
            value = parseExpression();
            eat(")");
            return value;
        }
        else  
        {
            return parseNumber();
        }
    }

    public boolean isInteger(String input)
    {
        try
        {
            Integer.parseInt( input );
            return true;
        }
        catch ( Exception e )
        {
            return false;
        }
    }

    public void parseStatement()
    {
        Map <String, Integer> hashmap = new  HashMap <String, Integer>();
        if (currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            int value = parseExpression();
            eat(")");
            eat(";");
            Syse m.out.println(value);
        }
        else if (currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            while (!(currentToken.equals("END")))
            {
                parseStatement();
            }
            eat("END");
            eat(";");
        }
        else
        {
            String var = currentToken;
            eat(currentToken);
            eat(":=");
            int value = parseExpr
            ession();
            
            
            eat(currentToken);
            
            
            hashmap.put(var, value);
        }
    }

    public int parseTerm()
    {
        int value = parseFactor();
        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if (currentToken.equals("*"))
            {
                eat("*");
                value = value * parseFactor();
            }
            if (currentToken.equals("/"))
            {
                eat("/");
                value = value / parseFactor();
            }
        }
        return value;
    }

    public int parseExpression()
    {
        int value = parseTerm();
        while (currentToken.equals("+") || currentToken.equals("-"))
        {
            if (currentToken.equals("+"))
            {
                eat("+");
                value = value + parseTerm();
            }
            if (currentToken.equals("-"))
            {
                eat("-");
                value = value - parseTerm();
            }
        }
        return value;
    }

    public static void main (String [] args) throws Exception
    {  
        FileInputStream inStream = 
            new FileInputStream(new File("/Users/rahulmehta/Documents/Documents - Rahul’s MacBook Air/CS/Scanner Lab/parser/parserTest4.txt"));
        Scanner scanner = new Scanner(i3Stream); 
        Parser parser = new Parser(scanner);
        while (scanner.hasNext())
        {
            parser.parseStatement();
        }
    }
}
