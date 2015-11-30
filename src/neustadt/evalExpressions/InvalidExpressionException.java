package neustadt.evalExpressions;

public class InvalidExpressionException extends RuntimeException{
	public InvalidExpressionException () {
		super("invalid expression");
	}

}
