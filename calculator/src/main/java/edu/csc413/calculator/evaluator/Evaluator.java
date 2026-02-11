package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer expressionTokenizer;
    private final String delimiters = " +/*-^()";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int evaluateExpression(String expression) throws InvalidTokenException {
        String expressionToken;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.expressionTokenizer = new StringTokenizer(expression, this.delimiters, true);

        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators
        // Push a left parenthesis as a sentinel so we never peek an empty stack.
        operatorStack.push(Operator.getOperator("("));

        while (this.expressionTokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(expressionToken = this.expressionTokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(expressionToken)) {
                    operandStack.push(new Operand(expressionToken));
                } else {
                    if (!Operator.check(expressionToken)) {
                        throw new InvalidTokenException(expressionToken);
                    }


                    // TODO Operator is abstract - these two lines will need to be fixed:
                    // The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example.
                    Operator newOperator = Operator.getOperator(expressionToken);

                    // If it's a left parenthesis, push to operator stack
                    if (newOperator instanceof LeftParenthesis) {
                        operatorStack.push(newOperator);
                        continue;
                    }

                    // If it's a right parenthesis, solve until left parenthesis
                    if (newOperator instanceof RightParenthesis) {
                        // Pop operators and evaluate until we hit a left parenthesis
                        while (!(operatorStack.peek() instanceof LeftParenthesis)) {
                            Operator operatorFromStack = operatorStack.pop();
                            // Ensure there are enough operands
                            if (operandStack.size() < 2) {
                                throw new InvalidTokenException("Insufficient operands");
                            }
                            Operand operandTwo = operandStack.pop();
                            Operand operandOne = operandStack.pop();
                            Operand result = operatorFromStack.execute(operandOne, operandTwo);
                            operandStack.push(result);
                        }
                        // pop the left parenthesis and discard
                        operatorStack.pop();
                        continue;
                    }

                    // For normal operators, evaluate any operators on the stack
                    // with priority >= new operator before pushing it.
                    while (operatorStack.peek().priority() >= newOperator.priority()) {
                        Operator operatorFromStack = operatorStack.pop();
                        // In case we accidentally encounter a left parenthesis, stop.
                        if (operatorFromStack instanceof LeftParenthesis) {
                            break;
                        }
                        if (operandStack.size() < 2) {
                            throw new InvalidTokenException("Insufficient operands");
                        }
                        Operand operandTwo = operandStack.pop();
                        Operand operandOne = operandStack.pop();
                        Operand result = operatorFromStack.execute(operandOne, operandTwo);
                        operandStack.push(result);
                    }

                    operatorStack.push(newOperator);
                }
            }
        }

        // After we've scanned all tokens, apply remaining operators until
        // we reach the sentinel left parenthesis.
        while (!(operatorStack.peek() instanceof LeftParenthesis)) {
            Operator operatorFromStack = operatorStack.pop();
            if (operandStack.size() < 2) {
                throw new InvalidTokenException("Insufficient operands");
            }
            Operand operandTwo = operandStack.pop();
            Operand operandOne = operandStack.pop();
            Operand result = operatorFromStack.execute(operandOne, operandTwo);
            operandStack.push(result);
        }

        // The final result should be the only operand on the stack
        if (operandStack.size() != 1) {
            throw new InvalidTokenException("Malformed expression");
        }

        return operandStack.pop().getValue();
    }
}