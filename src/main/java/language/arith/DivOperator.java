package language.arith;

import language.Operand;
import language.Operator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DivOperatorTest {
    Operator<Integer> operator;
    Operand<Integer> op0;
    Operand<Integer> op1;

    /**
     * Runs before each test.
     */
    @BeforeEach
    public void setup() {
        operator = new DivOperator();
        op0 = new Operand<>(5);
        op1 = new Operand<>(7);
    }

    @Test
    public void testPerformOperation() {
        operator.setOperand(0, op0);
        operator.setOperand(1, op1);

        Operand<Integer> result = operator.performOperation();
        int value = result.getValue();
        assertEquals(0,  value,
                "Operator applied to 5 and 7 should produce 0.");
    }

    @Test
    public void testPerformOperationPositiveNumbers() {
        operator.setOperand(0, new Operand<>(12));
        operator.setOperand(1, new Operand<>(4));

        Operand<Integer> result = operator.performOperation();
        int value = result.getValue();
        assertEquals(3,  value,
                "Operator applied to 12 and 4 should produce 3.");
    }

    @Test
    public void testPerformOperationNegativeNumbers() {
        operator.setOperand(0, new Operand<>(-20));
        operator.setOperand(1, new Operand<>(-5));

        Operand<Integer> result = operator.performOperation();
        int value = result.getValue();
        assertEquals(4,  value,
                "Operator applied to -20 and -5 should produce 4.");
    }

    @Test
    public void testPerformOperationNegativeResult() {
        operator.setOperand(0, new Operand<>(-20));
        operator.setOperand(1, new Operand<>(5));

        Operand<Integer> result = operator.performOperation();
        int value = result.getValue();
        assertEquals(-4,  value,
                "Operator applied to -20 and 5 should produce -4.");
    }

    @Test
    public void testPerformOperationZeroNumerator() {
        operator.setOperand(0, new Operand<>(0));
        operator.setOperand(1, new Operand<>(5));

        Operand<Integer> result = operator.performOperation();
        int value = result.getValue();
        assertEquals(0,  value,
                "Operator applied to 0 and 5 should produce 0.");
    }

    @Test
    public void testGetNumberOfArguments() {
        assertEquals(operator.getNumberOfArguments(), 2,
                "Binary operator should have 2 arguments.");
    }

    @Test
    public void testIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> operator.setOperand(2, op0),
                "Binary operator should not except input to position 2");
        assertThrows(IllegalArgumentException.class,
                () -> operator.setOperand(-1, op0),
                "Binary operator should not except input to position -1");
    }

    @Test
    public void testNullArgumentException() {
        assertThrows(NullPointerException.class,
                () -> operator.setOperand(0, null),
                "Operator should not allow null arguments");
    }

    @Test
    public void testIllegalStateException() {
        operator.setOperand(0, new Operand<Integer>(5));
        assertThrows(IllegalStateException.class,
                () -> operator.setOperand(0, new Operand<Integer>(12)),
                "Operator should not allow the same operand position to be set more than once");
    }

    @Test
    public void testIllegalStateExceptionPerform() {
        assertThrows(IllegalStateException.class,
                () -> operator.performOperation(),
                "Operator should not compute when all arguments have not been set.");
    }

    @Test
    public void testIllegalStateExceptionZeroDenominator() {
        assertThrows(IllegalStateException.class,
                () -> operator.setOperand(1, new Operand<Integer>(0)),
                "Operator should not allow the denominator to be set to zero");
    }
}
