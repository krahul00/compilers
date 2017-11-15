package ast;
import environment.Environment;

/**
 * WRITELN is the class when there is a 'WRITELN' present
 * 
 * @author Rahul Mehta
 * @version 10 October 2017
 */
public class Writeln extends Statement
{
    private Expression exp;
    /**
     * Constructor for writeln
     * @param exp Parameter passed to be printed out
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * After evaluating the expression, it will print it out
     * @param env Environment passed that allows the program to
     *          access or set variables
     * @Usage .exec(env);
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
