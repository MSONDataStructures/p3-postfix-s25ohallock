package evaluator.arith;

import evaluator.IllegalPostfixExpressionException;
import evaluator.Evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArithPostfixEvaluatorTest {

    private Evaluator<Integer> evaluator;

    @BeforeEach
    public void setup() {
        evaluator = new ArithPostfixEvaluator();
    }

    @Test
    public void testEvaluateSimple() {
        Integer result = evaluator.evaluate("1");
        assertEquals(Integer.valueOf(1), result);
    }

    @Test
    public void testEvaluatePlus() {
        Integer result = evaluator.evaluate("1 2 +");
        assertEquals(Integer.valueOf(3), result);

        result = evaluator.evaluate("1 2 3 + +");
        assertEquals(Integer.valueOf(6), result);

        result = evaluator.evaluate("10000 1000 100 10 1 + + + +");
        assertEquals(Integer.valueOf(11111), result);
    }

    @Test
    public void testEvaluateSub() {
        Integer result = evaluator.evaluate("1 2 -");
        assertEquals(Integer.valueOf(-1), result);

        result = evaluator.evaluate("1 2 3 - -");
        assertEquals(Integer.valueOf(2), result);

        result = evaluator.evaluate("1000 100 10 1 - - -");
        assertEquals(Integer.valueOf(909), result);
    }

    @Test
    public void testEvaluateMult() {
        Integer result = evaluator.evaluate("1 2 *");
        assertEquals(Integer.valueOf(2), result);

        result = evaluator.evaluate("1 2 3 * *");
        assertEquals(Integer.valueOf(6), result);

        result = evaluator.evaluate("1 2 3 4 * * *");
        assertEquals(Integer.valueOf(24), result);
    }

    @Test
    public void testEvaluateDiv() {
        Integer result = evaluator.evaluate("4 2 /");
        assertEquals(Integer.valueOf(2), result);

        result = evaluator.evaluate("8 4 2 / /");
        assertEquals(Integer.valueOf(4), result);

        result = evaluator.evaluate("16 8 4 2 / / /");
        assertEquals(Integer.valueOf(4), result);
    }

    @Test
    public void testEvaluateNegativeDiv() {
        Integer result = evaluator.evaluate("10 -5 /");
        assertEquals(Integer.valueOf(-2), result);

        result = evaluator.evaluate("-16 2 /");
        assertEquals(Integer.valueOf(-8), result);

        result = evaluator.evaluate("-27 -3 /");
        assertEquals(Integer.valueOf(9), result);
    }

    @Test
    public void testEvaluateDivZero() {
        Integer result = evaluator.evaluate("0 5 /");
        assertEquals(Integer.valueOf(0), result);

        result = evaluator.evaluate("0 -2 /");
        assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void testEvaluateIntegerDiv() {
        Integer result = evaluator.evaluate("15 2 /");
        assertEquals(Integer.valueOf(7), result);

        result = evaluator.evaluate("8 3 /");
        assertEquals(Integer.valueOf(2), result);

        result = evaluator.evaluate("16 5 /");
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    public void testEvaluateNegate() {
        Integer result = evaluator.evaluate("1 !");
        assertEquals(Integer.valueOf(-1), result);

        result = evaluator.evaluate("2 !");
        assertEquals(Integer.valueOf(-2), result);

        result = evaluator.evaluate("-15 !");
        assertEquals(Integer.valueOf(15), result);
    }

    @Test
    public void testEvaluateComplex() {
        Integer result = evaluator.evaluate("3 4 * 2 5 * +");
        assertEquals(Integer.valueOf(22), result);

        result = evaluator.evaluate("1 2 3 4 ! - * +");
        assertEquals(Integer.valueOf(15), result);

        result = evaluator.evaluate("3 4 ! - 2 * 1 +");
        assertEquals(Integer.valueOf(15), result);

        result = evaluator.evaluate("3 4 2 + * 5 *");
        assertEquals(Integer.valueOf(90), result);

        result = evaluator.evaluate("4 2 + 3 5 * *");
        assertEquals(Integer.valueOf(90), result);

        result = evaluator.evaluate("3 1 * 2 + 9 -");
        assertEquals(Integer.valueOf(-4), result);

        result = evaluator.evaluate("2 3 1 * + 9 -");
        assertEquals(Integer.valueOf(-4), result);

        result = evaluator.evaluate("5 1 2 + 4 * ! + 3 -");
        assertEquals(Integer.valueOf(-10), result);

        result = evaluator.evaluate("12 3 ! + 2 * 4 2 - 3 5 ! + * -");
        assertEquals(Integer.valueOf(22), result);
    }

    @Test
    public void testInvalidDivision() {
        assertThrows(IllegalStateException.class,
                () -> evaluator.evaluate("1 0 /"));
    }

    @Test
    public void testInvalidExpression() {
        assertThrows(IllegalPostfixExpressionException.class,
                () -> evaluator.evaluate("1 2"));
        assertThrows(IllegalPostfixExpressionException.class,
                () -> evaluator.evaluate("1 +"));
        assertThrows(IllegalPostfixExpressionException.class,
                () -> evaluator.evaluate("1 2 3 +"));
    }
}
