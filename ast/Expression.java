package ast;
import environment.Environment;

/**
 * The abstract class for Expression is the overaching 
 * class for Variable, BinOp, and Number
 * 
 * @author Rahul
 * @version 10 October 2017
 */
public abstract class Expression
{
    /**
     * Abstract method for evaluation that is passed down
     * to its subclasses
     * @return int value of the evaluated expression
     * @param env Environment that holds all the values
     */
    public abstract int eval(Environment env);
}
