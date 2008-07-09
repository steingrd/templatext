package org.javango.template.compiler;

import org.javango.template.Parser;
import org.javango.template.TemplateNode;
import org.javango.template.core.TextNode;
import org.javango.template.core.VariableNode;

public class TagCompiler {

	private TagLibrary library;
	private ExpressionCompiler expressionCompiler;
	
	public TagCompiler(TagLibrary tagLibrary, FilterLibrary filterLibrary) {
		this.library = tagLibrary;
		this.expressionCompiler = new ExpressionCompiler(filterLibrary);
	}

	public TemplateNode compile(Token token, Parser parser) {
		TokenType type = token.getType();
		
		if (type.equals(TokenType.COMMENT)) {
			return new TextNode("");
		}
		
		if (type.equals(TokenType.TEXT)) {
			return new TextNode(token.getContents());
		}
		
		if (type.equals(TokenType.VARIABLE)) {
			return new VariableNode(expressionCompiler.compile(token.getContents()));
		}
		
		if (type.equals(TokenType.TAG)) {
			return library.resolve(token.splitContents()[0]).parse(parser, token);
		}
		
		assert false;
		return null;
	}
	
}
