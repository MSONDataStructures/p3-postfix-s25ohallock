package parser.arith;

import language.Operand;
import language.Operator;
import parser.PostfixParser;
import parser.PostfixParser.Type;

public class ArithPostfixParserExample {

    // TONOTDO: please do not edit this class

    /**
     * Main method.
     * @param args - command line arguments passed into class
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        PostfixParser<Integer> parser = new ArithPostfixParser("1 2 +");

        boolean next = parser.hasNext(); // Returns true because there are more elements to be consumed
        Type nextP = parser.nextType(); // Returns OPERAND because the next element is an operand
        Operand<Integer> o0 = parser.nextOperand(); // Returns an operand wrapping 1
        next = parser.hasNext(); // Returns true because there are more elements to be consumed
        nextP = parser.nextType(); // Returns OPERAND because the next element is an operand
        Operand<Integer> o1 = parser.nextOperand(); // Returns an operand wrapping 2
        next = parser.hasNext(); // Returns true because there are more elements to be consumed
        nextP = parser.nextType(); // Returns OPERATOR because the next element is an operator
        Operator<Integer> operator = parser.nextOperator(); // Returns the PlusOperator
        next = parser.hasNext(); // Returns false because all the elements have been consumed
    }
}
