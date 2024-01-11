package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class MultiplyOperator extends Operator {
    @Override
    public int priority() {

        // addition priority = 2
        return 2;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {

        // returns the product of the two operand objects after converting
        // them to integers.
        return new Operand(operandOne.getValue() * operandTwo.getValue());
    }
}
