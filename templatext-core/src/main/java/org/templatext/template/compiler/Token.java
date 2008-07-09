package org.templatext.template.compiler;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Represents a single token in the template. A template is a series of 
 * tokens, separated by the Lexer, each token is either a Text, Variable,
 * Block or Comment token.
 * 
 * A template token has a type and a string representation, in this class
 * represented by the contents property. 
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class Token {

	private TokenType type;
	private String contents;
	
	public Token() {
	}
	
	public Token(TokenType type, String contents) {
		this.type = type;
		this.contents = contents;
	}
	
	/**
	 * Splits the contents property of a Token into an array of string elements.
	 * 
	 * TODO parse quoted strings
	 * 
	 * @return an array of Strings.
	 */
	public String[] splitContents() {
		return getContents().split(" ");
	}
	
	/**
	 * Returns the contents of this token as a String.
	 * 
	 * @return the contents of this token.
	 */
	public String getContents() {
		return contents;
	}
	
	/**
	 * Sets the value of the contents of this token.
	 * 
	 * @param contents the value of the contents of this token.
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	/**
	 * Returns the type of this token as a TokenType enumeration member.
	 * 
	 * @return the value to set.
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * Sets the type of this token as a TokenType enumeration member value.
	 * 
	 * @param tokenType the value to set.
	 */
	protected void setType(TokenType tokenType) {
		this.type = tokenType;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("type", type).append("contents", contents).toString();
	}
	
}