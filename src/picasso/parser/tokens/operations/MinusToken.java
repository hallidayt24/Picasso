package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the minus sign token
 * 
 * @author Reese Nelson
 */
public class MinusToken extends CharToken implements OperationInterface {
	
	public MinusToken() {
		super(CharConstants.MINUS);
	}
}
