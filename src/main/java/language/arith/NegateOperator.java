package language.arith;

import language.Operand;

public class NegateOperator extends UnaryOperator<Integer> {
    @Override
    public Operand<Integer> performOperation() {
        Operand<Integer> operand = getOperand();
        if (operand == null) {
            throw new IllegalStateException("Operand has not been set.");
        }
        return new Operand<>(-operand.getValue());
    }
}
