package postfix;

import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	private static final String PLUS     = "+";
	private static final String MINUS    = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE   = "/";

	private String arithmeticExpr;
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {
		Stack<Double> operands = new Stack<Double>();
		Scanner scanner = new Scanner(arithmeticExpr);

		while (!scanner.isEmpty()) {
			Token currToken = scanner.getToken();
			if (currToken.isDouble()) {
				operands.push(currToken.getValue());
			} else {
				// evaluate expression so far
				double operand2;
				double operand1;
				if (operands.empty()) {
					throw new MalformedExpressionException();
				} else {
					operand2 = operands.pop();
					if (operands.empty()) {
						throw new MalformedExpressionException();
					} else {
						operand1 = operands.pop();
					}
				}

				String operator = currToken.getName();
				if (operator.equals(PLUS)) {
					operands.push(operand1 + operand2);
				} else if (operator.equals(MINUS)) {
					operands.push(operand1 - operand2);
				} else if (operator.equals(MULTIPLY)) {
					operands.push(operand1 * operand2);
				} else if (operator.equals(DIVIDE)) {
					operands.push(operand1 / operand2);
				} else {
					throw new MalformedExpressionException();
				}
			}
			scanner.eatToken();
		}
		
		return operands.peek();
	}
	
}