package neustadt.evalExpressions;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class IncompleteEvalExpression {
	private Stack<String> opStack;

	public IncompleteEvalExpression() {
		opStack = new Stack<String>();
	}

	public String PostFix(String InfixExpression)
			throws InvalidExpressionException {
		// convert the String to a set of tokens - the whitespace will be
		// used to divide up the String into a set of tokens

		StringTokenizer infix = new StringTokenizer(InfixExpression);

		String token;
		String topToken;
		char operator;
		StringBuffer postFixExp = new StringBuffer(); // contains PostFix
														// expression
		final String BLANK = " ";
		while (infix.hasMoreElements()) {
			token = infix.nextToken();
			// since + in an expression means one or more occurrences when we
			// want to compiler to treat it as the character +
			// we must preface it with a \ but since the backslash also has
			// special meaning we preface the \ with another \
			// so now it becomes \\+ to compare to a + sign
			// we have to do the same thing for a * since the * means zero or
			// more occurrences in a string!

			if (token.matches("[\\+-/\\*()]")) {
				operator = token.charAt(0);
				// this is a mathematical operator or a parenthesis
				switch (operator) {
				case '(':
					opStack.push(token);
					break;
				case ')':
					for (;;) {
						try {
							topToken = opStack.pop();
							if (topToken.charAt(0) == '(')
								break;
							else {
								postFixExp.append(BLANK);
								postFixExp.append(topToken);
							}
						} catch (EmptyStackException e) {
							// doesn't have a matching open parenthesis
							throw new InvalidExpressionException();

						}
					}
					break;

				case '+':
				case '-':
				case '*':
				case '/':
					for (;;) {
						char topTok;
						if (!opStack.empty())
							topTok = opStack.peek().charAt(0);
						else
							topTok = ' '; // set it to blank
						if (opStack.empty() || topTok == '('
								|| (operator == '*' || operator == '/')
								&& (topTok == '+' || topTok == '-')) {
							opStack.push(token);
							break;

						} else {
							topToken = opStack.pop();
							postFixExp.append(BLANK + topToken);

						}
					}
					break;
				}

			} else {
				// it is a number , just place it into the expression
				postFixExp.append(BLANK + token);

			}

		}
		while (!opStack.empty()) { // pop what is left in the operator stack
			topToken = opStack.pop();
			if (topToken.charAt(0) != '(') {
				postFixExp.append(BLANK + topToken);
			} else {
				// found an unmatched ( parenthesis
				throw new InvalidExpressionException();

			}
		}

		return postFixExp.toString();

	}

	public Double evalPostFix(String postFixExpression) {
		Stack<Double> stack = new Stack<Double>();
		StringTokenizer postfix = new StringTokenizer(postFixExpression);
		String token;
		char operator;
		double operand1;
		double operand2;

		while (postfix.hasMoreElements()) {
			token = postfix.nextToken();

			if (token.matches("[\\+-/\\*]")) {
				operator = token.charAt(0);

				if (stack.empty()) {
					throw new EmptyStackException();
				}
				operand2 = stack.pop();

				if (stack.empty()) {
					throw new EmptyStackException();
				}
				operand1 = stack.pop();

				// this is a mathematical operator
				switch (operator) {
				case '+':
					stack.push(operand1 + operand2);
					break;
				case '-':
					stack.push(operand1 - operand2);
					break;
				case '*':
					stack.push(operand1 * operand2);
					break;
				case '/':
					stack.push(operand1 / operand2);
					break;
				}
			} else {
				stack.push(Double.parseDouble(token));
			}
		}// end of postFixExpression

		if (stack.empty()) {
			throw new EmptyStackException();
		}
		double result = stack.pop();
		
		if(!stack.empty()){
			throw new InvalidExpressionException();
		}
		return result;
	}

	public static void main(String[] args) {

		IncompleteEvalExpression anExpression = new IncompleteEvalExpression();
		Scanner console = new Scanner(System.in);
		String completeExpression;

		Double value;
		System.out.println("Enter a complete algebraic expression");
		completeExpression = console.nextLine();
		String postFixExp;
		postFixExp = anExpression.PostFix(completeExpression);
		System.out.println("postfix " + postFixExp);

		value = anExpression.evalPostFix(postFixExp);
		System.out.println("result " + value);

	}
}
