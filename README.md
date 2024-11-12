# `DSp4Postfix` Postfix Evaluator

## Overview
For this assignment, you will be implementing a Postfix Evaluator to perform basic arithmetic.
This assignment is based off material in Chapter 3 and Chapter 4 in the Java Software Structures
textbook by Lewis & Chase. The code has several **packages**, as well as interfaces and a
custom exception for stack underflow.


### Table of Contents
**[Files to complete](#files-to-complete)**<br>
**[TODO vs TONOTDO](#TODO-vs-TONOTDO)**<br>
**[Part One: Importing the Project](#part-one-importing-the-project)**<br>
**[Part Two: Implementing LinkedStack](#part-two-implementing-linkedstack)**<br>
**[Part Three: Implement Arithmetic Operators](#part-three-implement-arithmetic-operators)**<br>
**[Part Four: Implement a Postfix Arithmetic Evaluator](#part-four-implement-a-postfix-arithmetic-evaluator)**<br>
**[Part Five: Commit Project](#part-five-commit-project)**<br>
**[Bonus: Infix Expression Evaluator](#bonus-infix-expression-evaluator)**<br>
**[Additional Notes: Help for the project](#additional-notes)**<br>

## Files to complete
Each of the files listed here have unit tests associated with them and are
required for submission. Except for `LinkedStack`, you are allowed to implement
them in any way you would like. However, keep in mind there are helper classes
that should make your job easier.

`stack.LinkedStack<T>` A stack data structure that you built in the previous
project. The `Node` class has been created for you to use with your stack,
please do not modify it. We again have a generic type with the type parameter `T`.
We will be using the stack primarily with `Integer` objects, but it should work
for any object type in place of `T`, such as with `String` objects. The only
difference with our previous implementation is that you need to throw our own
exception, `StackUnderflowException`, when you call `pop` or `top` on an
empty stack.

`language.arith.SubOperator / MultOperator / DivOperator` Binary operators
for performing subtraction / multiplication / division on two integers.

`language.arith.NegateOperator` A unary operator for performing negation
on a single integer. We use the symbol `!` instead of the usual minus sign.

`evaluator.arith.ArithPostfixEvaluator` An evaluator for simple arithmetic
postfix notation...this is the challenging part.

### Test files
In the test folder, you are provided with several JUnit test cases that will
help you keep on track while completing this assignment. I recommend you run
the tests often, and use them as a checklist of things to do next. If you
choose to modify these files, proceed with care, but in general you are welcome
to add any additional tests, or a driver class for that matter (see below).

### TODO vs TONOTDO
Several classes / interfaces in this project have been completed for you.
The places where you need to provide code are marked with a TODO comment,
and the classes / interfaces that you should not modify are marked with a
TONOTDO comment.

You do not need to understand how these classes are implemented, but it is
important that you understand what these classes offer. In particular, it is
highly recommended that you spend some time reading the Javadoc in each of
the following classes:

 * `stack.StackInterface`
 * `parser.PostfixParser`
 * `language.Operator`
 * `language.Operand`
 * `language.BinaryOperator`
 * `evaluator.PostfixEvaluator`


## Part One: Getting Started
First you need to get your GitHub Classroom project into your IDE.

By default, your project should have no compiler errors and contain
the following structure:

**src** - The source folder where all code you are submitting must go.
There are two modules within `src`, first is `main` where our class
packages go, and also `test` with the JUnit 5 test suite.<br>
**main** - There are four packages here, each with a separate folder.<br>
The `evaluator` package contains an `Evaluator` interface, a custom exception
called `IllegalPostfixExpressionException`, and an `arith` subpackage with a
`ArithPostfixEvaluator` skeleton class that will take some work.<br>
The `language` package contains an `Operator` interface, an `Operand` class
which serves as a wrapper class and should not be modified, a `BinaryOperator`
abstract class that implements the `Operator` interface, and an `arith`
subpackage with many operator subclasses to implement.<br>
The `parser` package with many arcane things you are welcome to check out.
There is a `PostfixParser` interface, an `ArithPostfixParser` that implements
it, and an `arith` subpackage with a small example of how the postfix evaluator works.<br>
The `stack` package has our stack implementation, with a `StackInterface`,
a `Node` class, the `LinkedStack` skeleton class, and our special `StackUnderflowException`.

Note that `main` and `test` are **modules**, which are like super-packages
(part of [Project Jigsaw](https://openjdk.org/projects/jigsaw/)!)
A module is a uniquely named, reusable group of related packages,
as well as resources and a module descriptor. If you go to the Project
Settings window you will see Module Settings, where you can find out
about module dependencies.

You will likely want to use some flavor of JDK 17 for this project.

If you are missing any of the above or errors are present,
seek help immediately, so you can get started on the project right away.

## Part Two: Implementing LinkedStack
Start by transferring over your work from the stack project to the
`stack` package, modifying your `pop` and `top` methods to throw our new
`StackUnderflowException` if the stack is empty.

## Part Three: Implement Arithmetic Operators
Before you can even attempt to create a postfix evaluator, you will need
to define what each of the possible postfix operators do. For this assignment,
you are required to support addition, subtraction, multiplication, division,
and negation of integers. To help facilitate this, you have been provided
with an `Operator<T>` interface. Take a moment to review the interface.

Now run the `language.arith.PlusOperatorTest` test. All the tests pass...right?
Next, open the `PlusOperator` class, and you will see an implementation.
Review this implementation, then complete the `SubOperator`, `DivOperator`,
and `MultOperator` classes. Each time you implement something, be sure to run
the associated tests to make sure everything is working correctly. You can
find the individual operator tests in the `language.arith` test folder.

Note that for the `DivOperator` you will have to override the `setOperand`
method to throw an `IllegalStateException` if the denominator is set to 0.
After checking for the exception you can call the `setOperand` method from
`BinaryOperator` and pass in the appropriate arguments.

These operators all have two operands, but we will also want to implement
an operator with one operand. You will need to complete the `UnaryOperator`
abstract class, which implements the `Operator` interface, and should look
a lot like the `BinaryOperator` abstract class, but simpler (because there
is only one operand).

Finally, you will need to implement the unary `NegateOperator` class,
which will extend the `UnaryOperator` class. Negation is represented by
the `!` symbol and flips the sign of the `Operand`. For example, "5 !"
would evaluate to -5, and "-12 !" would evaluate to 12.

## Part Four: Implement a Postfix Arithmetic Evaluator
Now that we have a stack and operators defined, it is time to create
an evaluator. Open up the `evaluator.arith.ArithPostfixEvaluator` class,
and you will see four TODO comments.

Before starting, check out the `evaluator.arith.ArithPostfixEvaluatorTest`
class to see examples of how the evaluator is expected to be called and
the results that are expected to be returned.

First, you want to initialize the stack you will be using with your implementation.

Second, determine what you will do when you see an `Operand`.

Third, determine what you will do when you see an `Operator`.

Finally, determine what you will return.

## Part Five: Commit Project
When you have finished your solution and are ready to submit, make your
final commit and push everything up to GitHub Classroom.

## Bonus: Infix Expression Evaluator
Implement the following algorithm for the evaluation of arithmetic infix
expressions for a spicy bonus. We will also throw in an exponentiation
operator, using the `^` symbol.

Each operator has a precedence. The + and - operators have the lowest precedence,
* and / have a higher (and equal) precedence,and ^ (which denotes "raising to a
* power" in this project) has the highest. For example,

3 * 4 ^ 2 + 5

should mean the same as

(3 * (4  ^ 2)) + 5

with a value of 53.

If you are going to tackle the bonus you will likely want to implement a robust test suite.

### Bonus Part One: Write an ExponentOperator class
Write an `ExponentOperatorTest` class similar to that of the `PlusOperatorTest`
class. Try to think of additional scenarios you would like to test and write
test cases for those scenarios.

Implement an `ExponentOperator` class that is similar to your `PlusOperator`
class. Think about what the two operands represent and how `performOperation`
would work for this class.

### Bonus Part Two: Add priorities and include exponent parsing
You will want to modify the `Operator` interface to include a `getPriority`
method, and then add that method to the operators that we have implemented.
You may assume for simplicity that the negation operator will not be included
in our infix expressions.

You can modify the `ArithPostfixEvaluator` class to include exponentiation,
or copy it over to a new `ArithInfixEvaluator` class that has an option for
the `ExponentOperator` in the `operators` hash map.

### Bonus Part Three: Write an ArithInfixEvaluatorClass
Write an `ArthInfixEvaluatorTest` class modeled after `ArthPostfixEvaluatorTest`
class. Try to think of additional scenarios you would like to test and write
test cases for those scenarios.

Implement an `ArthInfixEvaluator` class that is similar to your
`ArthPostfixEvaluator` class.

In your algorithm, use two stacks. One stack holds Operands, the other holds
Operators. When you encounter an Operand, put it on the Operand stack. When
you encounter an Operator, push it on the Operator stack if it has higher
precedence than the operator on the top of the stack. Otherwise, pop an Operator
off the Operator stack, then pop the appropriate number of Operands off the
Operand stack, and push the result of the computation on the Operand stack.
Repeat until the top of the Operator stack has lower precedence. At the end
of the expression, clear the stack in the same way. For example, here is how
the expression 3 * 4 ^ 2 + 5 is evaluated:

Expression: 3 * 4 ^ 2 + 5
```

1) Remaining expression: * 4 ^ 2 + 5           Operand stack               Operator stack 
                                                      3 

2) Remaining expression:   4 ^ 2 + 5           Operand stack               Operator stack 
                                                      3                            * 

3) Remaining expression:     ^ 2 + 5           Operand stack               Operator stack  
                                                      4                            *
                                                      3

4) Remaining expression:       2 + 5           Operand stack               Operator stack
                                                      4                            ^
                                                      3                            *  

5) Remaining expression:         + 5           Operand stack               Operator stack
                                                      2                            ^
                                                      4                            *
                                                      3  

6) Remaining expression:         + 5           Operand stack               Operator stack
                                                      16                           *          
                                                      3
 
7) Remaining expression:           5           Operand stack               Operator stack
                                                      48                           +

8) Remaining expression:                       Operand stack               Operator stack
                                                      5                            +
                                                      48
                                        
9) Remaining expression:                       Operand stack               Operator stack
                                                      53
```

## Additional Notes

### Using the ArithPostfixParser
You have been provided with a class for parsing arithmetic postfix expressions.
It is not important that you understand how it is implemented, but it is
important that you understand what the interface provides for you. Read over
the comments in the `parser.PostfixParser` interface carefully.

A short example of its use can be found in `parser.arith.ArithPostfixParserExample`

### Collaboration and Resources
This is a solo project, and you should write your own code. That said, feel free
to discuss the project with your classmates, with the understanding that there
is a difference between helping someone get to a solution and providing someone
with your own solution. Of course, copying code from the internet, or from an
Artificial Intelligence, is not the purpose of this project. Read through the
book and see if you can piece together the puzzle yourself. I am happy to help.

### Material on Exceptions
For this assignment, you will need to make use of exceptional situations.
For a quick reference on how to throw an exception, check out `language.BinaryOperator`,
an abstract class that meets many of the requirements of the `language.Operator`
interface. You will notice that its `setOperand` method has several exceptional
states and throws the exceptions detailed in `language.Operator`. Also, there is
material available in the book in chapter 3 (focus on section 3.6).

### Where is the Driver Class?
If you scan through the provided files, you will notice none of them contain a
`main` method. This means that out of the box you can’t actually run your code.
Instead, you might want to create your own drivers for testing out your elements.
For example, when you implement `MultOperator`, you could write a driver similar
to the following:

```java
public static void main(String[] args){
  Operator<Integer> multOp = new MultOperator();
  Operand<Integer> operand0 = new Operand<>(5);
  Operand<Integer> operand1 = new Operand<>(6);
  multOp.setOperand(0, operand0);
  multOp.setOperand(1, operand1);
  Operand<Integer> result = multOp.performOperation();
  System.out.println(result.getValue());
  System.out.println("Expected: 30");
}
```

Consider also writing a driver class that reads in postfix expressions from
the user and calculates them. This might look something like the following:

```java
public static void main(String[] args){
   Scanner s = new Scanner(System.in);
   Evaluator<Integer> evaluator = new ArithPostfixEvaluator();
   System.out.println("Welcome to the Postfix Evaluator 5000");
   System.out.println("Please enter a postfix expression to be evaluated:");
   String expr = s.nextLine();
   // Sometimes I get an exception. Maybe I should use a try/catch block?
   Integer result = evaluator.evaluate(expr);
   System.out.println("The expression evaluated to: " + result);
   // Maybe I could ask user if they want to enter another expression and loop?
}
```

### What is this enum Type?
In the `ArithPostfixEvaluator` code provided for you, there is a `switch`
statement that has two cases: `OPERAND` and `OPERATOR`. If you take a look,
you will find the following:

```java
	/**
	 * A {@link PostfixParser} can produce different types.
	 * @author jcollard, jddevaug
	 */
	enum Type {
	
		/**
		 * Indicates that the value being parsed is an {@link Operand}
		 */
		OPERAND,
		
		/**
		 * Indicates that the value being parsed is an {@link Operator} 
		 */
		OPERATOR
	}
```

The `PostfixParser` can produce two different types, `Operators` and `Operands`.
This class helps facilitate that information to the user in a more readable
(and modular) way than a boolean would. The `nextType` method returns one of
these two values indicating what you should do next in your evaluator.

Luckily, most of this code is written for you, so you don’t have to sweat it.
For more information about enumerated types, check out the following [link](http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html).
