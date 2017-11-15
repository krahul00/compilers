package environment;
import java.util.*;
import ast.ProcedureDeclaration ;

/**
 * The job of environments is to remember the values of variables.
 * 
 * @author Rahul Mehta
 * @version October 10, 2017
 */
public class Environment
{
    private Map <String, Integer> hashmap;
    private Map <String, ProcedureDeclaration> hashmap1;
    private Environment parent = null;
    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        hashmap = new HashMap <String, Integer>();
        hashmap1 = new HashMap <String, ProcedureDeclaration>();
    }

    /**
     * Simple accessor for the environment parents
     * @return parent environment
     */
    public Environment getParent()
    {
        return parent;
    }

    /** 
     * It keeps on accessing the parent environment until it is in
     * the global environment. When it is, it checks if it the variable
     * is there. If not, it puts it in the global environment. If yes,
     * it puts it in the local environment.
     * @param variable variable key
     * @param value int value
     */
    public void setVariable(String variable, int value)
    {
        Environment temp = this;
        while (temp.getParent()  != null && !temp.hashmap.containsKey(variable))
        {
            temp = temp.getParent();               
        }
        if (temp.getParent() == null) // redundancy test :)
            temp.hashmap.put(variable, value);
        else
            hashmap.put(variable, value);
    }

    /**
     * Declares variable in local environment
     * @param variable name of variable
     * @param value value of variable
     */
    public void declareVariable(String variable, int value)
    {
        hashmap.put(variable, value);
    }

    /**
     * Gets the value associated with the variable in the hash map
     * Iterates through all the environments/ parent environments until
     * it finds the variable
     * @return the int associated with the variable provided
     * @param variable a valid string representing a variable 
     */
    public int getVariable(String variable)
    {
        Environment temp = this;
        if (temp.hashmap.containsKey(variable))
        {
            return (hashmap.get(variable));
        }
        else
        {
            while (temp.getParent() != null && 
                !temp.hashmap.containsKey(variable))
            {
                temp = temp.getParent();               
            }
            if (temp.hashmap.containsKey(variable))
                return (temp.hashmap.get(variable));
            return 0;    
        }
    }

    /**
     * Sets a passed environment as the parent of the current env
     * @param env passed environment
     */
    public void setParent(Environment env)
    {
        parent = env;
    }

    /** 
     * Sets entries in the hash map
     * @param variable variable key
     * @param value int value
     */
    public void setProcedure(String variable, ProcedureDeclaration value)
    {
        Environment temp = this;
        while (temp.getParent() != null)
        {
            temp = temp.getParent();
        }
        temp.hashmap1.put(variable, value);
    }

    /**
     * Gets the value associated with the variable in the hash map
     * @return the int associated with the variable provided
     * @param variable a valid string representing a variable 
     */
    public ProcedureDeclaration getProcedure(String variable)
    {
        Environment temp = this;
        while (temp.getParent() != null)
        {
            temp = temp.getParent();
        }
        return (temp.hashmap1.get(variable));
    }
}
