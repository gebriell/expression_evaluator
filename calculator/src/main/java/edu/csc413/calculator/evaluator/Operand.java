package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

    // Instance variable to store the operand value
    private int value;

    /**
     * construct operand from string token.
     */
    public Operand(String token) {

        if (check(token)) if (check(token)) {
            // Parse and store the value
            this.value = Integer.parseInt(token);
        } else {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid operand token: " + token);
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {

        // passes the int value to the operand object
        this.value = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {

        // returns the operand object's value
        return value;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {

        // check if token is a valid operand (integer),
        // return token.matches("\\d+"):
        try {
            Integer.parseInt(token);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
