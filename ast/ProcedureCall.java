package ast;
import java.util.*;
import ast.*;
import environment.*;

/**
 * ProcedureCall is a class that takes care of calling a procedure
 * with parameters. As such, it also provides support
 * for the evaluation of such ProcedureCalls
 * 
 * @author Rahul Mehta
 * @version November 13, 2017
 */
public class ProcedureCall extends Expression
{
    String name;
    List<Expression> express;
    /**
     * Constructor for procedurecalls
     * @param name1 name of procedure
     * @param listofexpressions list of expressions
     */
    public ProcedureCall(String name1, List<Expression> listofexpressions)
    {
        name = name1;
        express = listofexpressions;
    }

    /**
     * Evaluates the procedure with the passed environment
     * @return integer value of the result of the procedure
     * @param env environment that the variables are stored in
     */
    public int eval(Environment env)
    {
        Environment hangingenvironment = new Environment();
        hangingenvironment.setParent(env);
        Environment temp = env;
        while (temp.getParent() != null)
        {
            temp = temp.getParent();
        }
        ProcedureDeclaration dec = temp.getProcedure(name);
        hangingenvironment.setVariable(name, 0);
        ArrayList<String> params = (ArrayList <String>) dec.getParams();
        for (int i = 0; i < params.size(); i++)
        {
            hangingenvironment.setVariable(params.get(i),
                express.get(i).eval(env));
        }
        hangingenvironment.getProcedure(name).getStatement()
            .exec(hangingenvironment);
        return hangingenvironment.getVariable(name);
    }
    
}
