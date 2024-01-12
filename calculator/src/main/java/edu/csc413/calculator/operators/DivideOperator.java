package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class DivideOperator extends Operator{
    @Override
    public int priority() {

        // division priority = 2
        return 2;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {

        // returns the quotient of the two operand objects after converting
        // them to integers.
        return new Operand(operandOne.getValue() / operandTwo.getValue());
    }
}
