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
        Operand<Integer> op0 = this.getOp0();
        Operand<Integer> op1 = this.getOp1();
        if (op0 == null || op1 == null) {
            throw new IllegalStateException("Could not perform operation prior to operands being set.");
        }

        // Check for division by zero
        if (op1.getValue() == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        Integer result = op0.getValue() / op1.getValue();
        return new Operand<>(result);
    }

    /**
     * {@inheritDoc}
     * This method is overridden to check for division by zero when operands are set.
     */
    @Override
    public void setOperand(int i, Operand<Integer> operand) {
        // Ensure that operand i is set to the appropriate operand
        super.setOperand(i, operand);

        // Additional check to prevent division by zero during operand assignment
        if (i == 1 && operand.getValue() == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
    }
}
