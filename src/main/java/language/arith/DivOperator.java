package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@link DivOperator} performs integer division on two operands.
 *
 * @author jcollard
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
            throw new IllegalStateException("Cannot perform operation: operands are not set.");
        }

        if (op1.getValue() == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        Integer result = op0.getValue() / op1.getValue();
        return new Operand<>(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOperand(int position, Operand<Integer> operand) {
        if (operand == null) {
            throw new NullPointerException("Operand cannot be null.");
        }
        if (position == 1 && operand.getValue() == 0) {
            throw new IllegalStateException("Denominator cannot be zero.");
        }
        super.setOperand(position, operand);
    }


}
