package ast;
import environment.Environment;

/**
 * Conditions are the instances of the latter part
 * of if statements
 * 
 * @author Rahul Mehta
 * @version 10 October 2017
 */
public class Condition
{
    Expression exp1;
    Expression exp2;
    String relop;
    /**
     * Constructor for objects of class Condition
     * It is based off the grammar
     * @param param1 expression that is the first part of the cond
     * @param param2 expression that is the second part of the cond
     * @param param3 String that holds the operator
     */
    public Condition(Expression param1, Expression param2, String param3)
    {
        exp1 = param1;
        exp2 = param2;
        relop = param3;
    }

    /**
     * Evaluation of a conditional. Essentially it just checks the evaluation
     * of each expression in real life, and then returns a boolean
     * @param env environment that holds all values for variables
     * @return true if the check returns true
     *          false if the check returns false
     *  @Usage .eval(env);
     */
    public boolean eval (Environment env)
    {
        if (relop.equals("="))
            return exp1.eval(env) == exp2.eval(env);
        if (relop.equals(">"))
            return exp1.eval(env) > exp2.eval(env);
        if (relop.equals("<"))
            return exp1.eval(env) < exp2.eval(env);
        if (relop.equals(">= "))
            return exp1.eval(env) >= exp2.eval(env);
        if (relop.equals("<="))
            return exp1.eval(env) <= exp2.eval(env);
        return exp1.eval(env) != exp2.eval(env);
    }
}
