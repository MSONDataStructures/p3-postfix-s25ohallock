package parser;

import language.Operand;
import language.Operator;

/**
 * A {@link PostfixParser} is used to parse postfix expressions made up of operands and operators.
 * @author jcollard, jddevaug
 * @param <T> - the type of data held in an operand
 */
public interface PostfixParser<T> {

    // TONOTDO: please do not edit this class

    /**
     * A {@link PostfixParser} can produce different types.
     * @author jcollard, jddevaug
     *
     */
    enum Type {
        /**
         * Indicates that the value being parsed is an {@link Operand}.
         */
        OPERAND,

        /**
         * Indicates that the value being parsed is an {@link Operator}.
         */
        OPERATOR
    }

    /**
     * Returns {@code true} if this parser has more parsables and {@code false} otherwise.
     * @return {@code true} if this parser has more parsables and {@code false} otherwise.
     */
    boolean hasNext();

    /**
     * Returns the type of the next parsable.
     * @return the type of the next parsable.
     * @throws IllegalStateException if there are no more parsables.
     */
    Type nextType();

    /**
     * Returns the next {@link Operand}.
     * @return the next {@link Operand}.
     * @throws IllegalStateException if the next parsable was not an Operand
     *         or there are no more parsables.
     */
    Operand<T> nextOperand();

    /**
     * Returns the next {@link Operator}.
     * @return the next {@link Operator}.
     * @throws IllegalStateException if the next parsable was not an {@link Operator}
     *         or there are no more parsables.
     */
    Operator<T> nextOperator();
}
