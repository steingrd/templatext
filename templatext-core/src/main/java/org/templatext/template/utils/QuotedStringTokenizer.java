package org.templatext.template.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuotedStringTokenizer implements Iterator<String>, Iterable<String> {

	private String string;

	private int currentPosition;

	public QuotedStringTokenizer(String string) {
		this.string = string.trim();
		this.currentPosition = 0;
	}

	@Override
	public boolean hasNext() {
		return currentPosition < string.length();
	}

	@Override
	public String next() {
		int start = currentPosition;
		boolean quotedString = false;
		char quoteCharacter = '0';
		
		while (currentPosition < string.length()) {
			if (isQuote()) {
				if (quotedString) {
					char c = string.charAt(currentPosition);
					if (c == quoteCharacter) {
						currentPosition++;
						break;
					}
				} else {
					quotedString = true;
					quoteCharacter = string.charAt(currentPosition);
				}
			}
			
			if (!quotedString && isWhitespace()) {
				break;
			}
			
			currentPosition++;
		}

		// skip whitespace, leave currentPosition at the next valid character
		int end = currentPosition;
		while (isWhitespace()) {
			currentPosition++;
		}

		String token = string.substring(start, end);
		
		// strip away quote characters
		if (quotedString) {
			token = token.substring(1, token.length() - 1);
		}
		
		return token;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}

	public String[] allTokens() {
		List<String> list = new ArrayList<String>();
		for (String string : this) {
			list.add(string);
		}
		return list.toArray(new String[] {});
	}

	private boolean isQuote() {
		if (string.length() < currentPosition) {
			return false;
		}
		char c = string.charAt(currentPosition);
		return c == '"' || c == '\'';
	}
	
	private boolean isWhitespace() {
		return currentPosition < string.length() && Character.isWhitespace(string.charAt(currentPosition));
	}

}
