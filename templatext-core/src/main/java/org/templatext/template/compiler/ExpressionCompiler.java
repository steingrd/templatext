package org.templatext.template.compiler;

import org.templatext.template.core.FilterChain;
import org.templatext.template.core.FilterExpression;

public class ExpressionCompiler {

	private FilterLibrary library;
	
	public ExpressionCompiler(FilterLibrary library) {
		this.library = library;
	}
	
	public FilterExpression compile(String expression) {
		if (!expression.contains("|")) {
			return new FilterExpression(expression);
		}
		
		String[] parts = expression.split("\\|");	
		return new FilterExpression(parts[0], collect(parts));
	}

	private  FilterChain collect(String[] parts) {
		FilterChain chain = new FilterChain();
		for (int i = 1; i < parts.length; i++) {
			String name = extractFilterName(parts[i]);
			String arg = extractFilterArgument(parts[i]);
			chain.add(library.resolve(name), arg);
		}
		return chain;
	}

	private String extractFilterName(String expr) {
		if (!expr.contains(":")) {
			return expr;
		}
		
		return expr.split(":")[0];
	}

	private String extractFilterArgument(String expr) {
		if (!expr.contains(":")) {
			return null;
		}
		
		String argument = expr.split(":")[1];
		
		if (argument.startsWith("'") || argument.startsWith("\"")) {
			argument = argument.substring(1, argument.length() - 1);
		}
		
		return argument;
	}
	
	public FilterLibrary getLibrary() {
		return library;
	}
	
}
