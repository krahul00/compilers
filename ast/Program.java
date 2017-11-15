package ast;
import environment.*;
import java.util.*;

/**
 * A program may begin with an arbitrary 
 * number of procedure declarations, eventually 
 * followed by a stmt and a period.
 * 
 * @author rahul mehta
 * @version november 13, 2017
 */
public class Program
{
    private Statement stmt;

    /**
     * Constructor for objects of class Program
     * @param param statements of the program
     */
    public Program(Statement param)
    {
        stmt = param;
    }

    /**
     * Executes the program by passing the environment to
     * the statement and executing it there
     * @param env passed environemtn
     */
    public void exec (Environment env)
    {
        stmt.exec(env);
    }
    /*
    public void run(Environment env)
    {
    for (int i = 0; i < procedurelist.size(); i++)
    {
    procedurelist.get(i)
    }
     */
}
