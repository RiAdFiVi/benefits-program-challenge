package coe.unosquare.benefits.order;

/**
 * Exception to be thrown when the amount of products is not valid
 *
 */
@SuppressWarnings("serial")
public class IncorrectAmountException extends Exception{
	public IncorrectAmountException(String message) {
		super(message);
	}
}
