package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class AddOperator extends Operator{

    @Override
    public int priority() {

        // addition priority = 1
        return 1;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {

        // returns the sum of the two operand objects after converting
        // them to integers.
        return new Operand(operandOne.getValue() + operandTwo.getValue());
    }
}
