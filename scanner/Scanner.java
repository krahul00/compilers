package scanner;
import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters 
 * (2014-2015) Lab Exercise 1
 * @author Rahul Mehta
 * @version Version 1, September 6, 2017
 * Usage: This input scanner is responsible for reading input strings,
 * determining  the individual lexemes according to the given
 * set of rules, and producing a string of tokens.
 * In addition to lexical analysis of the input, 
 * the scanner performs other functions such as eliminating comments. 
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar; //pointer to current character
    private boolean eof; //whether it is end of file
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input, which represents 
     * an input of info bytes.
     * Usage: Scanner lex = new Scanner(inStream); 
     * FileInputStream inStream = new FileInputStream 
     * (new File(<file name>);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false; //setting the boolean controlling the eof as false
        getNextChar(); //setting the pointer to the first character
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag 
     * an then reads the first character of the input string into 
     * the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Sets the instance field currentChar to the value read 
     * from the input stream using the  read method. Typecasts 
     * the result of the "read" method to a Char. Gives a -1 
     * if end of file. Also, it catches IOExceptions.
     * Usage: getNextChar();
     */
    private void getNextChar()
    {
        try // makes sure to check if there is an exception
        {
            int read = in.read(); //sets the variable to what it has read
            if (read == -1) 
            {  
                eof = true; 
            }
            else
            {
                currentChar = (char)(read); 
            }
        } 
        catch (IOException e) 
        { 
            System.out.println("You have an IOErrorException");
        }
    }

    /**
     * Compares the value of the input parameter to currentChar and 
     * if they are the same, it advances the input stream one 
     * character by calling getNextChar(). If the values differ,
     * the method throws a ScanErrorException.
     * @usage: eat(currentChar);
     * @param expected This represents the expected value of currentChar
     */
    private void eat(char expected) throws ScanErrorException
    { 
        if (currentChar == (expected)) 
        { 
            getNextChar(); //advances the pointer
        }
        else 
        {  
            throw new ScanErrorException("Illegal character - expected "
                + (expected) +  " and found " + (currentChar)); 
        }
    }

    /**
     * Checks if the eof boolean is true or false
     * and reports the opposite of the valuee
     * @return false if eof == true
     *          true if eof == false
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Takes in a char and returns a boolean depending on
     * whether or not the character is a digit. 
     * @Usage: isDigit(currentChar);
     * @param c the currentChar that the method tries to 
     * identify whether or not it is a digit
     * @return true if param is a digit
     *         false if param is not a digit
     */
    public static boolean isDigit(char c)
    {
        return (c >= '0'  && c <= '9');
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
     * Takes in a char and returns a boolean depending on whether
     * or not the character is a white space. 
     * @Usage: isWhiteSpace(currentChar);
     * @param c the currentChar that the method tries to identify 
     * whether or not it is a white space
     * @return true if param is a whiteSpace
     *         false if param is not a white Space
     */
    public static boolean isWhiteSpace(char c)
    {
        return (c == ' ') || ( c == '\t' ) || (c == '\r' ) || (c == '\n');
    }

    /**
     * Takes in a char and returns a boolean depending on whether or not
     * the character is an operand. 
     * @Usage: isOperand(currentChar);
     * @param c the currentChar that the method tries to identify
     * whether or not it is an operand
     * @return true if param is an operand
     *         false if param is not an operand
     */
    public static boolean isOperand (char c)
    {
        if (("!+-<>,.=%()*;:").indexOf(c) != -1) //easy way 
        //of checking all operands
        {       
            return true;            
        }
        return false;
    }

    /**
     * This method returns a String that represents a number. 
     * It uses a while statement to continue adding numbers to the String.
     * When it is done adding a character, it eats it. When it encounters a 
     * character that is not a number, it returns the number. 
     * @Usage: scanNumber();
     * @return: A string that represents a number such as '10'
     */
    private String scanNumber() throws ScanErrorException 
    {
        String number = "";
        while (hasNext() && isDigit(currentChar)) //checks if char is digit
        //and that it is not at the eof otherwise it would continually eat
        {
            number += currentChar; //appending it
            eat(currentChar);
        }
        return number;
    }

    /**
     * This method returns a String that represents a word 
     * such as Bob or BOB123. 
     * It uses a while statement to continue adding letters to the String.
     * When it is done adding a character, it eats it. When it encounters a 
     * character that is not a letter or digit, it returns the word. 
     * @Usage: scanIdentifier();
     * @return: A string that represents a word such as 'Rahul'
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String word = "";
        if (!isLetter(currentChar)) // checks whether or not the first char
        // is a letter
        {
            throw new ScanErrorException("Expecting Letter");
        }
        while (hasNext() && (isLetter(currentChar) || isDigit(currentChar)))
        { 
            word += currentChar; // appends itd
            eat(currentChar); 
        }
        return word;
    }

    /**
     * This method returns a String that represents operands. 
     * When it is done adding the character, it eats it. 
     * @Usage: scanOperand();
     * @return: A string that represents an operand like '%'.
     */
    private String scanOperand() throws ScanErrorException
    {
        String operand = "";
        if (isOperand(currentChar)) 
        {  
            if ((currentChar == ':') || 
                (currentChar == '<') || (currentChar == '>'))
            {
                operand += currentChar;
                eat(currentChar);
                if (operand.equals("<"))
                {
                    if (currentChar == '>')
                    {
                        operand += currentChar;
                        eat(currentChar);                        
                    }
                }
                if (currentChar == '=')
                {
                    operand += currentChar;
                    eat(currentChar); 
                }
                else if (operand.equals(":"))
                {
                    throw new ScanErrorException("Found : without =");
                }
                return operand;
            }
            else
            {
                operand += currentChar;
                eat(currentChar);
            }
        }
        return operand;
    }

    /**
     * Skips any leading white space and then examines the value
     * of currentChar, calling the appropriate method to scan for 
     * the next token in the input stream. 
     * @return Returns a String representing the lexeme found. 
     *          Returns the value “END” if the input stream is at
     *          end- of-file when nextToken is called.
     * @usage scanner.nextToken();
     */
    public String nextToken() throws ScanErrorException
    {
        String tokenString = "";
        if (!hasNext()) 
        { 
            tokenString = "END";
            return "."; 
        }
        while (isWhiteSpace(currentChar)) 
        { 
            eat(currentChar); 
        }
        if (currentChar == '/')
        {
            eat(currentChar);
            if (currentChar == '/')
            {
                while ((currentChar) != '\n')
                {
                    eat(currentChar);
                }
                eat(currentChar);
            }
            else if (currentChar == '*')
            {
                eat(currentChar);
                while ((currentChar) != '*')
                {
                    eat(currentChar);
                }
                eat(currentChar);
                eat(currentChar);
                while (isWhiteSpace(currentChar)) 
                { 
                    eat(currentChar); 
                }
            }
            else
            {
                return ("/");
            }
        }
        while (isWhiteSpace(currentChar)) 
        { 
            eat(currentChar); 
        }
        if (isLetter(currentChar)) 
        { 
            tokenString = scanIdentifier(); 
        }
        else if (isOperand(currentChar)) 
        {
            tokenString = scanOperand(); 
        } 
        else if (isDigit(currentChar))
        { 
            tokenString = scanNumber();
        }
        else
        {
            System.out.print(currentChar);
            throw new ScanErrorException("Unknown Lexeme " + currentChar); 
        }
        return tokenString;
    }    

    /**
     * Tester for the code
     * @param args stack
     */
    public static void main  (String [] args) throws Exception
    {  
        ///Users/rahulmehta/Documents/Documents - 
        //Rahul’s MacBook Air/CS/Scanner Lab/
        FileInputStream inStream = 
            new FileInputStream(new File("ScannerTest.txt"));
        Scanner scanner = new Scanner(inStream); 
        while (scanner.hasNext())
        {
            System.out.println(scanner.nextToken());
        }   
    }
}