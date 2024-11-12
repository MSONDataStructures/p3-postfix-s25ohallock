package evaluator.arith;

import evaluator.Evaluator;
import language.Operand;
import parser.arith.ArithPostfixParser;
import stack.StackInterface;

/**
 * An {@link ArithPostfixEvaluator} is a post fix evaluator
 * over simple arithmetic expressions.
 */
public class ArithPostfixEvaluator implements Evaluator<Integer> {

    private final StackInterface<Operand<Integer>> stack;

    /**
     * Constructs an {@link ArithPostfixEvaluator}.
     */
    public ArithPostfixEvaluator() {
        //TODO Initialize to your LinkedStack
        stack = null;
    }

    /**
     * Evaluates a postfix expression.
     * @return the result
     */
    @Override
    public Integer evaluate(String expr) {
        // TODO Use all the things built so far to create
        //   the algorithm for postfix evaluation
        ArithPostfixParser parser = new ArithPostfixParser(expr);
        while (parser.hasNext()) {
            switch (parser.nextType()) {
                case OPERAND:
                    //TODO What do we do when we see an operand?
                    break;
                case OPERATOR:
                    //TODO What do we do when we see an operator?
                    break;
                default:
                    //TODO If we get here, something went very wrong
            }
        }

        //TODO What do we return?
        return null;
    }
}
