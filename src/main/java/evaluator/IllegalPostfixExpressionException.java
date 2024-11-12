package evaluator;

/**
 * A {@link IllegalPostfixExpressionException} is thrown at runtime
 * when an invalid PostfixExpression is encountered.
 * @author jcollard, jddevaug
 */
public class IllegalPostfixExpressionException extends RuntimeException {

    // TONOTDO: please do not edit this class

    private static final long serialVersionUID = 1L;

    public IllegalPostfixExpressionException() {
        super();
    }

    public IllegalPostfixExpressionException(String message) {
        super(message);
    }
}
