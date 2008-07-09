package org.templatext.template.compiler;

import java.util.List;

import org.templatext.template.compiler.Lexer;
import org.templatext.template.compiler.Token;
import org.templatext.template.compiler.TokenType;

import junit.framework.TestCase;

public class LexerTest extends TestCase {
	
	private Lexer lexer;
	private List<Token> tokens;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		lexer = new Lexer();
	}
	
	public void testSimpleTokens() throws Exception {
		// single variable
		tokens = lexer.tokenize("{{ variable }}");
		assertEquals(1, tokens.size());
		assertEquals(TokenType.VARIABLE, tokens.get(0).getType());
		assertEquals("variable", tokens.get(0).getContents());
		
		// two variables
		tokens = lexer.tokenize("{{ var1 }}{{ var2 }}");
		assertEquals(2, tokens.size());
		assertEquals(TokenType.VARIABLE, tokens.get(0).getType());
		assertEquals("var1", tokens.get(0).getContents());
		assertEquals(TokenType.VARIABLE, tokens.get(1).getType());
		assertEquals("var2", tokens.get(1).getContents());
		
		// variables without whitespace
		tokens = lexer.tokenize("{{variable}}");
		assertEquals(1, tokens.size());
		assertEquals(TokenType.VARIABLE, tokens.get(0).getType());
		assertEquals("variable", tokens.get(0).getContents());
	
		// comments
		tokens = lexer.tokenize("{# comment #}");
		assertEquals(1, tokens.size());
		assertEquals(TokenType.COMMENT, tokens.get(0).getType());
		assertEquals("comment", tokens.get(0).getContents());
	}
	
	public void testSimpleTokensAndText() throws Exception {
		// two variables mixed with text
		tokens = lexer.tokenize("first {{ var1 }} second third {{ var2 }} fourth");
		
		assertEquals(5, tokens.size());
		
		assertEquals(TokenType.TEXT, tokens.get(0).getType());
		assertEquals("first ", tokens.get(0).getContents());
		
		assertEquals(TokenType.VARIABLE, tokens.get(1).getType());
		assertEquals("var1", tokens.get(1).getContents());
		
		assertEquals(TokenType.TEXT, tokens.get(2).getType());
		assertEquals(" second third ", tokens.get(2).getContents());
		
		assertEquals(TokenType.VARIABLE, tokens.get(3).getType());
		assertEquals("var2", tokens.get(3).getContents());
		
		assertEquals(TokenType.TEXT, tokens.get(4).getType());
		assertEquals(" fourth", tokens.get(4).getContents());
	}
	
	public void testMixedTokensAndText() throws Exception {
		tokens = lexer.tokenize(" some text {# a comment #} {{ variable }} space {% tag %} more words ");
		
		assertEquals(7, tokens.size());
		
		assertEquals(TokenType.TEXT, tokens.get(0).getType());
		assertEquals(" some text ", tokens.get(0).getContents());
		
		assertEquals(TokenType.COMMENT, tokens.get(1).getType());
		assertEquals("a comment", tokens.get(1).getContents());
		
		assertEquals(TokenType.TEXT, tokens.get(2).getType());
		assertEquals(" ", tokens.get(2).getContents());
		
		assertEquals(TokenType.VARIABLE, tokens.get(3).getType());
		assertEquals("variable", tokens.get(3).getContents());
		
		assertEquals(TokenType.TEXT, tokens.get(4).getType());
		assertEquals(" space ", tokens.get(4).getContents());

		assertEquals(TokenType.TAG, tokens.get(5).getType());
		assertEquals("tag", tokens.get(5).getContents());
		
		assertEquals(TokenType.TEXT, tokens.get(6).getType());
		assertEquals(" more words ", tokens.get(6).getContents());
	}
}
