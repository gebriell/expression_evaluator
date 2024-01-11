package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.InvalidTokenException;
import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    // encapsulate hashmap using private
    // have hashmap be shared for every instance using static
    // if we don't use static, each instance will have its own hashmap
    // declared outside static for scope purposes
    private static Map<String, Operator> operators;

    // executes once when loaded to memory
    static {
        operators = new HashMap<>();
        operators.put("+", new AddOperator());
        // operators.put( "-", new SubtractOperator() );
        // operators.put( "-", new MultiplyOperator() );
        // operators.put( "-", new DivideOperator() );
        // operators.put( "-", new PowerOperator() );
    }

    /**
     * retrieve the priority of an Operator
     *
     * @return priority of an Operator as an int
     */
    public abstract int priority();

    /**
     * Abstract method to execute an operator given two operands.
     *
     * @param operandOne first operand of operator
     * @param operandTwo second operand of operator
     * @return an operand of the result of the operation.
     */
    public abstract Operand execute(Operand operandOne, Operand operandTwo);

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token) {

        // return the proper operator from the
        // string object

        // if token is valid, it will be
        // equivalent to one of the keys
        // in our operator hashmap

        if (check(token)) {
            return operators.get(token);
        }
        return null;
    }


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {

        // instead of comparing the operators
        // to our token, we compare the token
        // to the operator keys in our hashmap

        return operators.containsKey(token);
    }
}
