package org.javango.template.compiler;

import java.util.Iterator;
import java.util.List;

public class TokenStream implements Iterator<Token> {

	private List<Token> tokens;
	private int position = 0;
	
	public TokenStream(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	public Token peek() {
		return tokens.get(position);
	}
	
	public Token next() {
		return tokens.get(position++);
	}

	public boolean hasNext() {
		return tokens.size() > position;
	}

	public void remove() {
		throw new RuntimeException("not supported");
	}
	
}
