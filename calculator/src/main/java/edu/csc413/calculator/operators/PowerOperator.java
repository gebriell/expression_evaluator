package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override
    public int priority() {

        // power priority = 3
        return 3;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {

        // returns the power of the two operand objects after converting
        // them to integers.
        return new Operand((int) Math.pow(operandOne.getValue(), operandTwo.getValue()));
    }
}
