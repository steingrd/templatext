package org.templatext.template.compiler;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.templatext.template.TemplateException;
import org.templatext.template.TemplateSyntaxErrorException;

/**
 * The Lexer takes a template string and divides the string into a
 * sequence of template tokens. 
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class Lexer {

	/**
	 * Takes a template string and divides into a sequence of template
	 * tokens. If the string is empty or null, an empty list is returned.
	 * 
	 * @param string template string that should be tokenized
	 * @return a List of Token objects for the given template string
	 * @throws TemplateException 
	 */
	public List<Token> tokenize(String templateString) {
		List<Token> tokens = new ArrayList<Token>();
		StringReader in = new StringReader(templateString);
		int c = 0;
		StringBuilder currentToken = new StringBuilder(1024);
		
		try {
			while ((c = in.read()) != -1) {
				if (c == '{') {
					// check if next character is {, # or % then read the token
					c = in.read();

					// if we've reached the end it is just text at the end of
					// the template
					if (c == -1) {
						currentToken.append((char) c);
						break;
					} else if (c == '{' || c == '%' || c == '#') {
						// create a token of what is already in currentToken
						// and then create a token for this current tag
						if (currentToken.length() > 0) {
							tokens.add(new Token(TokenType.TEXT, currentToken.toString()));
							currentToken.delete(0, currentToken.length());
							currentToken.setLength(0);
						}
						// now create a token for this tag
						tokens.add(readAndCreateSpecialToken((char)c, in));
					} else {
						currentToken.append((char) c);
					}
				} else {
					currentToken.append((char) c);
				}
			}
		} catch (IOException e) {
			throw new TemplateException("error while reading template", e);
		}
		
		if (currentToken.length() > 0) {
			tokens.add(new Token(TokenType.TEXT, currentToken.toString()));
		}
		
		return tokens;
	}

	private Token readAndCreateSpecialToken(char tagType, StringReader in) {
		StringBuilder sb = new StringBuilder();

		int c = 0;
		try {
			while (c != -1) {

				c = in.read();

				if (c == -1) {
					throw new TemplateSyntaxErrorException("reached end of template while reading tag");
				} else if (c == '%' || c == '}' || c == '#') {
					// read one more to see if we're at the end of the tag
					
					int next = in.read();
					if (next == -1) {
						throw new TemplateSyntaxErrorException("reached end of template while reading tag");
					} 
					
					if (next == '%' || next == '}' || next == '#') {
						break;
					} else {
						sb.append((char) c);
						sb.append((char) next);
					}
				} else {
					sb.append((char) c);
				}
			}
		} catch (IOException e) {
			throw new TemplateException("error while reading template", e);
		}

		switch (tagType) {
		case '{':
			return new Token(TokenType.VARIABLE, sb.toString().trim());
		case '%':
			return new Token(TokenType.TAG, sb.toString().trim());
		case '#':
			return new Token(TokenType.COMMENT, sb.toString().trim());
		default:
			throw new IllegalArgumentException("not a valid token character:" + tagType);
		}
	}
}
