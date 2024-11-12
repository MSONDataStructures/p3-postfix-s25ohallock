package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedStackTest {
    private StackInterface<Integer> intStack;
    private StackInterface<String> strStack;

    @BeforeEach
    public void setup() {
        intStack = new LinkedStack<Integer>();
        strStack = new LinkedStack<String>();
    }

    @Test
    public void testPushEmptyTopSize() {
        assertTrue(intStack.isEmpty(), "Stack should be empty after being constructed.");
        assertEquals(0, intStack.size(), "Stack should contain zero elements after being constructed.");

        intStack.push(5);
        assertFalse(intStack.isEmpty(), "Stack should not be empty.");
        assertEquals(Integer.valueOf(5), intStack.top(), "The top element should be 5");
        assertEquals(1, intStack.size(), "The stack should contain one element.");

        intStack.push(4);
        assertEquals(Integer.valueOf(4), intStack.top(), "The top element should be 4");
        assertEquals(2, intStack.size(), "The stack should contain two elements.");

        intStack.push(3);
        assertEquals(Integer.valueOf(3), intStack.top(), "The top element should be 4");
        assertEquals(3, intStack.size(), "The stack should contain two elements.");

        intStack.push(2);
        assertEquals(Integer.valueOf(2), intStack.top(), "The top element should be 4");
        assertEquals(4, intStack.size(), "The stack should contain two elements.");
    }

    @Test
    public void testIntStack() {
        intStack.push(72);
        intStack.push(58);
        intStack.push(39);
        intStack.push(15);
        intStack.push(-7);
        assertFalse(intStack.isEmpty(), "Integer stack has been loaded up.");
        assertEquals(Integer.valueOf(-7), intStack.top(), "The top element should be -7");
        assertEquals(5, intStack.size(), "The integer stack should contain five elements.");

        Integer t = intStack.pop();
        assertEquals(Integer.valueOf(-7), t, "The popped element should be -7");
        assertEquals(Integer.valueOf(15), intStack.top(), "The top element should be 15");
        assertEquals(4, intStack.size(), "The integer stack should contain four elements.");
        assertFalse(intStack.isEmpty(), "The integer stack should not be empty.");

        intStack.pop();
        intStack.pop();
        intStack.pop();
        t = intStack.pop();
        assertEquals(Integer.valueOf(72), t, "The popped element should be 72");
        assertTrue(intStack.isEmpty(), "The integer stack should be empty.");
    }

    @Test
    public void testStrStack() {
        strStack.push("seventy-two");
        strStack.push("fifty-eight");
        strStack.push("thirty-nine");
        strStack.push("fifteen");
        strStack.push("negative seven");
        assertFalse(strStack.isEmpty(), "String stack has been loaded up.");
        assertEquals("negative seven", strStack.top(), "The top element should be \"negative seven\"");
        assertEquals(5, strStack.size(), "The string stack should contain five elements.");

        String s = strStack.pop();
        assertEquals("negative seven", s, "The popped element should be \"negative seven\"");
        assertEquals("fifteen", strStack.top(), "The top element should be \"fifteen\"");
        assertEquals(4, strStack.size(), "The string stack should contain four elements.");
        assertFalse(strStack.isEmpty(), "The string stack should not be empty.");

        strStack.pop();
        strStack.pop();
        strStack.pop();
        s = strStack.pop();
        assertEquals("seventy-two", s, "The popped element should be \"seventy-two\"");
        assertTrue(strStack.isEmpty(), "The string stack should be empty.");
    }

    @Test
    public void testBigStack() {
        int stackSize = 1000000;

        // no stack overflow here!
        for (int i = 0; i < stackSize; i++) {
            intStack.push(i);
            assertEquals(i, intStack.top());
            assertEquals(i + 1, intStack.size());
        }

        // clear out all this garbage!
        for (int i = stackSize; i > 0; i--) {
            assertEquals(i - 1, intStack.pop());
        }
        assertTrue(intStack.isEmpty());
    }

    @Test
    public void testStackUnderflowPop() {
        assertThrows(StackUnderflowException.class,
                () -> intStack.pop(),
                "Pop on an empty stack should throw underflow.");
    }

    @Test
    public void testStackUnderflowTop() {
        assertThrows(StackUnderflowException.class,
                () -> intStack.top(),
                "Examining the top of an empty stack should throw underflow.");
    }

    @Test
    public void testNullPointer() {
        assertThrows(NullPointerException.class,
                () -> intStack.push(null),
                "Pushing a null value should throw an exception.");
    }
}
