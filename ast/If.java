package ast;
import environment.Environment;

/**
 * Class that details an if-statement
 * 
 * @author Rahul Mehta
 * @version October 10, 2017
 */
public class If extends Statement
{
    Statement statement;
    Condition condition;
    
    /**
     * Constructor for objects of class If
     * @param stat expression after the "then"
     * @param cond Thats what the if statement checks
     */
    public If(Statement stat, Condition cond)
    {
        statement = stat;
        condition = cond;
    }

    /**
     * Executable method for if classes.
     * It is just a simple if statement
     * @param env Environment that holds all variables
     * @Usage .exec(env);
     */
    public  void exec(Environment env)
    {
        if (condition.eval(env))
            statement.exec(env);
        
    }

}
