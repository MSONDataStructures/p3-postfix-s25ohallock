package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@link DivOperator} is an operator that performs integer division on two
 * integers.
 * @author jcollard, jddevaug
 */
public class DivOperator extends BinaryOperator<Integer> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Operand<Integer> performOperation() {
        // TODO: Follow the example from PlusOperator to override
        //   this method (from the version in BinaryOperator)
        //   for division.
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOperand(int i, Operand<Integer> operand) {
        // TODO: For division we need to additionally override
        //  the setOperand method to check for division by zero.
        //  See DivOperatorTest (and the README) for usage.
    }
}
