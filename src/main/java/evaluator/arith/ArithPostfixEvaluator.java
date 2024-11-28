package evaluator.arith;
import evaluator.Evaluator;
import language.Operand;
import language.Operator;
import parser.arith.ArithPostfixParser;
import stack.StackInterface;
import stack.LinkedStack;
import evaluator.IllegalPostfixExpressionException;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator
 * over simple arithmetic expressions.
 */
public class ArithPostfixEvaluator implements Evaluator<Integer> {

    private final StackInterface<Operand<Integer>> stack;

    /**
     * Constructs an {@link ArithPostfixEvaluator}.
     */
    public ArithPostfixEvaluator() {
        // Initialize the stack
        stack = new LinkedStack<>();
    }

    /**
     * Evaluates a postfix expression.
     * @return the result
     */
    @Override
    public Integer evaluate(String expr) {
        ArithPostfixParser parser = new ArithPostfixParser(expr);

        while (parser.hasNext()) {
            switch (parser.nextType()) {
                case OPERAND:
                    Operand<Integer> operand = parser.nextOperand();
                    stack.push(operand);
                    break;
                case OPERATOR:
                    Operator<Integer> operator = parser.nextOperator();
                    int numArgs = operator.getNumberOfArguments();
                    Operand<Integer>[] operands = new Operand[numArgs];

                    for (int i = numArgs - 1; i >= 0; i--) {
                        if (stack.isEmpty()) {
                            throw new IllegalPostfixExpressionException("Not enough operands on the stack.");
                        }
                        operands[i] = stack.pop();
                    }

                    for (int i = 0; i < numArgs; i++) {
                        operator.setOperand(i, operands[i]);
                    }

                    Operand<Integer> result = operator.performOperation();
                    stack.push(result);
                    break;
                default:
                    throw new IllegalStateException("Unexpected token type.");
            }
        }

        if (stack.size() != 1) {
            throw new IllegalPostfixExpressionException("Invalid postfix expression. Stack should contain exactly one operand at the end.");
        }
        return stack.pop().getValue();
    }


}
