package ast;
import environment.Environment;

/**
 * This abstract class holds the exec method
 * for all of its subclasses
 * 
 * @author Rahul
 * @version October 10 2017
 */
public abstract class Statement
{
    /**
     * Abstract method for its subclasses for executing the 
     * evaluation of the expression
     * @param env Environment being passed to retrieve or set
     *            the values
     */
    public abstract void exec(Environment env);
}
