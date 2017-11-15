package ast;
import environment.Environment;
/**
 *
 * Number are the classes that hold a number
 * 
 * @author Rahul Mehta
 * @version October 10, 2017
 */
public class Number extends Expression
{
    private int value;
    /**
     * Constructor for objects of class Number
     * @param param int number that has to be stored in this class
     */
    public Number(int param)
    {
        this.value = param;
    }

    /**
     * Evaluatory method for Number. All it does it
     * return it
     * @return int that is the value of the number
     * @param env Environment that holds all the values
     * @Usage .eval(env);
     */
    public int eval(Environment env)
    {   
        return value;
    }

}
