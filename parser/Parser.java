package parser;
import scanner.Scanner;
import java.io.*;
import java.util.*;
import ast.Number;
import ast.Expression;
import ast.BinOp;
import ast.Statement;
import ast.Writeln;
import ast.*;
import environment.Environment;

/**
 * This parser creates a tree, which can be easily evaluated in the next stage. 
 * This tree does not depend on the original programming language syntax. 
 * Instead, this abstract syntax tree (AST) 
 * uses the abstract relationships between the statements and subexpressions.
 * @author Rahul Mehta
 * @version Version 1, September 27, 2017
 */
public class Parser
{
    /**
     * Scanner is a scanner initialized that should get the 
     * input streams of tokens to work with
     */
    public Scanner scanner; //be able to get the input streams of token
    String currentToken; //contains the currenttoken
    Map <String, Integer> hashmap = new 
        HashMap <String, Integer>(); //map, or the 
    //parser table, and it contains vars
    /**
     * This is the constructor for the Parser. It takes in a Scanner and 
     * stores it to an instance variable. Additionally, it calls the 
     * Scanner's next method, to set the pointer of currentToken to the 
     * first token.
     * Usage: Parser parser = new Parser(scanner);
     * @param scannertouse the scanner that is passed to the constructor
     */
    public Parser(Scanner scannertouse)
    {
        scanner = scannertouse; //setting the param to the instance var
        try 
        {
            currentToken = scannertouse.nextToken(); //calls nextToken
        }
        catch (Exception e)
        {
            System.out.println("You have a scanErrorException." +
                "Something is wrong with your constructor or scanner");
        }
    }

    /**
     * The eat method takes in a String representing the expected token. 
     * If the expected token matches the current token, then eat asks the
     * Scanner for the next token and stores that as the current token. 
     * @param expectedtoken the expectedtoken you compare to the currentToken
     * Usage: eat(currentToken);
     */
    public void eat(String expectedtoken) 
    {
        if (currentToken.equals(expectedtoken))
        {
            try
            {
                currentToken = scanner.nextToken(); //sets currentToken to the
                //next Token
            }
            catch (Exception e)
            {
                System.out.println("Hi, you have a scanErrorException");
            }
        }
        else
        {
            throw new IllegalArgumentException (expectedtoken 
                + "expected, but found " + currentToken);
        }
    }

    /**
     * If the current token is an integer and parseNumber is called, the method
     * will parse the token for the number value.
     * Then, it will eat it and return
     * the number in an instance of the Number class
     * Usage: parseNumber();
     * @return a new instance of the Number class
     */
    private Number parseNumber()
    {
        int num = Integer.parseInt(currentToken);
        eat(currentToken);
        return new Number(num);
    }

    /**
     * parseFactor kicks in when there is a -, (, or a variable.
     * If there a -, it multiplies a -1 to the value of the recursively
     * called expression by use of an instance of BinOp. If there is a '(',
     * it eats that token and parses the value of the resulting expression.
     * If there is a variable, it 
     * creates an instance of the Variable class. Else, it knows that the only
     * option left is a number, so it parses it and returns the value.
     * @return the parsed expression 
     * Usage: parseFactor();
     */
    public Expression parseFactor()
    {
        if (currentToken.equals("-"))
        {
            eat("-");
            return new BinOp("*", new Number(-1), parseFactor());
        }
        else if (currentToken.equals("("))
        {
            Expression value;
            eat("("); 
            value = parseExpression();
            eat(")");
            return value;
        }
        else if (isLetter(currentToken.charAt(0)))
        {
            String x = currentToken;
            eat(currentToken);
            if (currentToken.equals("("))
            {
                eat("(");
                ArrayList <Expression> expressionlist = 
                    new ArrayList<Expression>();
                while (!(currentToken.equals(")")))
                {
                    expressionlist.add(parseExpression());
                    if (currentToken.equals(","))
                    {
                        eat(",");
                    }
                }
                eat(")");
                return new ProcedureCall(x, expressionlist);
            }
            return new Variable(x);
        }
        else
        {
            return parseNumber();
        }    
    }

    /**
     * This method should keep parsing procedure declarations 
     * long as the current token is PROCEDURE. Then it should parse
     * a single statement.
     * @param env passed environment
     * @return Program object that has paresed all procedure declarations
     */
    public Program parseProgram(Environment env)
    {       
        List <ProcedureDeclaration> temp;
        while (currentToken.equals("PROCEDURE"))
        {
            ArrayList <String> array = new ArrayList <String>();
            eat("PROCEDURE");
            String name = currentToken;
            eat(currentToken);
            eat("(");
            while (!(currentToken.equals(")")))
            {
                array.add(currentToken);
                eat(currentToken);
                if (currentToken.equals(","))
                {
                    eat(",");
                }
            }
            eat (")");
            eat (";");
            Statement stot = parseStatement();
            ProcedureDeclaration proc =
                new ProcedureDeclaration(name, stot, array);
            proc.exec(env);
            //temp.add(proc);
        }
        Statement stmt = parseStatement();
        eat(".");
        // return new Program(stmt, temp);
        return new Program(stmt);
    }

    /**
     * Takes in a char and returns a boolean depending on
     * whether or not the character is a letter. 
     * @Usage: isLetter(currentChar);
     * @param c the currentChar that the method tries to identify
     * whether or not it is a letter
     * @return true if param is a letter
     *         false if param is not a letter
     */
    public static boolean isLetter(char c)
    {
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }

    /**
     * parseStatement() eats all tokens associated with the WRITELN 
     * statement. Additionally, parseStatement() uses the use of the
     * Expression class to parse what is in the WRITELN. If its a WRITELN,
     * it returns a instance of the WRITELN class. If there is a begin, it 
     * makes a instance of the Block class. If it is an IF statement, it creates
     * an instance of the IF class, and so on with a WHILE statement. 
     * @return Statement statement that is parsed
     * Usage: parseStatement();
     */
    public Statement parseStatement()
    {
        ArrayList <Statement> arraylist = new ArrayList <Statement> ();

        if (currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(exp);
        }
        else if (currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            while (!(currentToken.equals("END")))
            {
                arraylist.add(parseStatement());
            }
            eat("END");
            eat(";");
            return new Block(arraylist);
        }
        else if (currentToken.equals("IF"))
        {
            eat("IF");
            Expression value = parseExpression();
            String relop = currentToken;
            eat(relop);
            Expression value1 = parseExpression();
            eat("THEN");
            return new If( parseStatement(), 
                new Condition(value, value1, relop));
        }
        else if (currentToken.equals("WHILE"))
        {
            eat("WHILE");
            Expression value = parseExpression();
            String relop = currentToken;
            eat(relop);
            Expression value1 = parseExpression();
            eat("DO");
            return new While( parseStatement(), 
                new Condition(value, value1, relop));
        }
        else
        {
            String var = currentToken;
            eat(currentToken);
            eat(":=");
            Expression value = parseExpression();
            eat(";");
            return new Assignment(var, value);
        }
    }

    /**
     * parseTerm() is enacted when there is a * or a /. If there is a
     * * or / or %, it creates a BinOp.  
     * @return the parsed expression
     * Usage parseTerm()
     */
    public Expression parseTerm()
    {
        Expression value = parseFactor();
        while (currentToken.equals("*") || 
            currentToken.equals("/") || currentToken.equals("%"))
        {
            if (currentToken.equals("*"))
            {
                eat("*");
                value = new BinOp("*", value,  parseFactor());
            }
            if (currentToken.equals("/"))
            {
                eat("/");
                value = new BinOp("/", value,  parseFactor());
            }
            if (currentToken.equals("%"))
            {
                eat("/");
                value = new BinOp("%", value,  parseFactor());
            }
        }
        return value;
    }

    /**
     * parseExpression() takes care of the situation when a '+' or
     * a '-' is present. In essence, it creates a binOP as well
     * @return It returns a parsed expressionw
     * Usage parseExpression()
     */
    public Expression parseExpression()
    {
        Expression value = parseTerm();
        while (currentToken.equals("+") || currentToken.equals("-"))
        {
            if (currentToken.equals("+"))
            {
                eat("+");
                value = new BinOp("+", value,  parseFactor());
            }
            if (currentToken.equals("-"))
            {
                eat("-");
                value = new BinOp("-", value,  parseFactor());
            }
        }
        return value;
    }

    /**
     * Tester for the code
     * @param args stack
     */
    public static void main (String [] args) throws Exception
    {    
        FileInputStream inStream = 
            new FileInputStream 
            (new File("/Users/rahulmehta/Documents/Documents - Rahul’s MacBook Air/CS/Scanner Lab/parser/parserTest7.txt"));
        Scanner scanner = new Scanner(inStream); 
        Parser parser = new Parser(scanner);
        Environment env = new Environment();
        while (scanner.hasNext())
            parser.parseProgram(env).exec(env);

    }
}
