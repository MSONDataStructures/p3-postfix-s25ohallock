package language.arith;

import language.Operand;
import language.Operator;

public abstract class UnaryOperator<T> implements Operator<T> {
    private Operand<T> operand;
    private boolean operandSet;

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    public void setOperand(int position, Operand<T> operand) {
        if (position != 0) {
            throw new IllegalArgumentException("Unary operators only accept operand at position 0.");
        }
        if (operand == null) {
            throw new NullPointerException("Operand cannot be null.");
        }
        if (operandSet) {
            throw new IllegalStateException("Operand has already been set.");
        }
        this.operand = operand;
        operandSet = true;
    }

    protected Operand<T> getOperand() {
        if (!operandSet) {
            throw new IllegalStateException("Operand has not been set.");
        }
        return operand;
    }
}
