package stack;

/**
 * An interface for a last-in-first-out (LIFO) stack structure,
 * similar to the <code>java.util.Stack</code> class.
 * @author jcollard, jddevaug
 * @param <T> the type of elements in the stack
 */
public interface StackInterface<T> {
    // TONOTDO: please do not edit this class

    /**
     * Removes the top most element on this stack. For convenience,
     * the top most element is returned
     * @return the top most element of this stack is returned
     * @throws StackUnderflowException if the stack is empty.
     */
    T pop() throws StackUnderflowException;

    /**
     * Returns the top most element of this stack.
     * @return the top most element of this stack.
     * @throws StackUnderflowException if the stack is empty
     */
    T top() throws StackUnderflowException;

    /**
     * Pushes {@code elem} to the top of this stack.
     * @throws NullPointerException if {@code elem} is null
     */
    void push(T elem);

    /**
     * Returns {@code true} if the stack contains no elements and {@code false} otherwise.
     * @return {@code true} if the stack contains no elements and {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     * @return the number of elements in this stack.
     */
    int size();
}
