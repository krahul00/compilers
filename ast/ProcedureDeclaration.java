package ast;
import java.util.*;
import environment.*;
/**
 * This is a class that takes care of creating a procedure
 * 
 * @author Rahul Mehta
 * @version November 13, 2017
 */
public class ProcedureDeclaration
{
    String name;
    Statement stmt;
    List <String> params;
    
    /**
     * Constructor for objects of class ProcedureDeclaration
     * @param param name of procedure
     * @param param1 the Statement with the ProcedureDeclaration
     * @param listofparams the arguments of the procedure declaration
     */
    public ProcedureDeclaration(String param, Statement param1, 
        List<String> listofparams)
    {
        name = param;
        stmt = param1;
        params = listofparams;
    }

    /**
     * Sets the preocedure in the environment
     * @param env passed environment
     */
    public void exec(Environment env)
    {
        env.setProcedure(name, this);
    }
    
    /**
     * Simple accessor that gets name of procedure
     * @return name of procedure
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Simple accessor that gets the procedure's statements
     * @return statement of procedure dec.
     */
    public Statement getStatement()
    {
        return stmt;
    }

    /**
     * Simple accessor that gets the procedure's arguments
     * @return arguments of procedure dec.
     */
    public List<String> getParams()
    {
        return params;
    }
}
    

