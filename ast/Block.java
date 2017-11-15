package ast;
import java.util.*;
import environment.Environment;

/**
 * Blocks are the result of a BEGIN/END block. It 
 * consists of several statements.
 * 
 * @author Rahul
 * @version 10 October 2017
 */
public class Block extends Statement
{
    private List<Statement> stmts;
    
    /**
     * Constructor for the block.
     * @param param list of statements that it takes in
     *              thats present between the BEGIN/END
     */
    public Block(List<Statement> param)
    {
        stmts = param;
    }
    
    /**
     * Method for executing block statements. Essentially,
     * it executes each statement one by itself.
     * @param env Environment that will allow it to set or access
     *          values
     *  @Usage .exec(env);
     */
    public void exec(Environment env)
    {
        for (int i = 0; i < stmts.size(); i++)
        {
            stmts.get(i).exec(env);
        }
    }
}
