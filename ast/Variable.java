package ast;
import environment.Environment;

/**
 * Classes for instances of variables
 * 
 * @author Rahul Mehta
 * @version 10 October 2017
 */
public class Variable extends Expression
{
    private String name;
    /**
     * Constructor for objects of class Variable
     * @param param string that is the name of the variable
     */
    public Variable(String param)
    {
        this.name = param;
    }

    /**
     * Evaluatory method for variables. It just gets the variable
     * from the environment
     * @param env Environment that holds all the variables and its values
     * @return int value of the variable
     * @Usage .eval(env);
     */
    public int eval(Environment env)
    {
        
        return env.getVariable(name);
    }
    
}
