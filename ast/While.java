package ast;
import environment.Environment;

/**
 * This class is the instance of a while loop
 * 
 * @author Rahul Mehta
 * @version 10 October 2017
 */
public class While extends Statement
{
    Statement statement;
    Condition condition;
    /**
     * Constructor for objects of class If
     * @param stat Statement that is the second part 
     *        of the while loop
     * @param cond Conditional that is checked
     */
    public While(Statement stat, Condition cond)
    {
        statement = stat;
        condition = cond;
    }

    /**
     * Executable method for While loops. It just uses
     * a simple while loop to continually execute it
     * @param env Environment that holds the variables
     * @Usage .exec(env);
     */
    public  void exec(Environment env)
    {
        while (condition.eval(env))
            statement.exec(env);
    }
}
