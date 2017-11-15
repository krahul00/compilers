package ast;
import environment.Environment;

/**
 * Class that essentially assigns a value to a 
 * variable
 * 
 * @author  Rahul Mehta
 * @version October 10, 2017
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;
    /**
     * Constructor for objects of class Assignment 
     * @param param String that is the variable name
     * @param param1 Expression that is equal to the variable
     */
    public Assignment(String param, Expression param1)
    {
        var = param;
        exp = param1;
    }

    /**
     * Executable method for the assignment class. All it does
     * is set the variables in the environment.
     * @param env Environment where the variables can be set and 
     * accessed
     *  @Usage .exec(env);
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }

}
