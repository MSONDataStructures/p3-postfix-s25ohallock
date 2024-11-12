package evaluator;

/**
 * An {@link Evaluator} evaluates post fix expressions.
 * @author jcollard, jddevaug
 * @param <T> the type of result to be evaluated
 */
public interface Evaluator<T> {

    // TONOTDO: please do not edit this class

    /**
     * Evaluates the post fix expression and returns a value.
     * @param expr the expression to be evaluated
     * @return the value of evaluating expr
     * @throws IllegalPostfixExpressionException if the expression is not a valid post fix expression
     */
    public T evaluate(String expr);
}
