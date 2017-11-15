package ast;
import environment.Environment;
/**
 * The BinOp class consists of operations of between expressions
 * 
 * @author Rahul Mehta
 * @version 10 October 2017
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for objects of class BinOp
     * @param param string that describes the operator
     * @param x expression to be evaluated
     * @param y second expressino to be evaluated
     */
    public BinOp(String param, Expression x, Expression y)
    {
        op = param;
        exp1 = x;
        exp2 = y;
    }

    /**
     * The evaluation method for a BinOp. Based off what
     * the operator is, it returns the value of the evaluated 
     * expressions
     * @param env Environment passed that allows the easy retrieval
     * and setting of variables
     * @return int value of the evaluated expression
     * @Usage .eval(env);
     */
    public int eval(Environment env)
    {
        if (op.equals("*"))
            return exp1.eval(env) * exp2.eval(env);
        if (op.equals("/"))
            return exp1.eval(env) / exp2.eval(env);
        if (op.equals("%"))
            return exp1.eval(env) % exp2.eval(env);
        if (op.equals("+"))
            return exp1.eval(env) + exp2.eval(env);
        return exp1.eval(env) -  exp2.eval(env);
    }
}
