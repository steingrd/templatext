package org.javango.template.compiler;

import org.javango.template.Context;
import org.javango.template.Parser;
import org.javango.template.TemplateNode;
import org.javango.template.TemplateTagParser;
import org.javango.template.core.TextNode;
import org.javango.template.core.VariableNode;
import org.javango.template.test.ReflectionUtils;

import junit.framework.TestCase;

public class TagCompilerTest extends TestCase {

	private TagCompiler tagCompiler;
	private TagLibrary tagLibrary;
	private FilterLibrary filterLibrary;
	private Token token;
	private Parser parser;
	private TemplateNode node;
	
	@Override
	protected void setUp() throws Exception {
		tagLibrary = new TagLibrary();
		filterLibrary = new FilterLibrary();
		tagCompiler = new TagCompiler(tagLibrary, filterLibrary);
	}
	
	public void testCommentTokenReturnsEmptyTextNode() throws Exception {
		token = new Token(TokenType.COMMENT, "some text");
		node = tagCompiler.compile(token, parser);
		
		assertEquals(TextNode.class, node.getClass());
		assertEquals("", ReflectionUtils.getPrivateMember(node, "text"));
	}
	
	public void testTextTokenReturnsTextNode() throws Exception {
		token = new Token(TokenType.TEXT, "some text");
		node = tagCompiler.compile(token, parser);
		
		assertEquals(TextNode.class, node.getClass());
		assertEquals("some text", ReflectionUtils.getPrivateMember(node, "text"));
	}
	
	public void testVariableTokenReturnsVariableNode() throws Exception {
		token = new Token(TokenType.VARIABLE, "variable");
		node = tagCompiler.compile(token, parser);
		
		assertEquals(VariableNode.class, node.getClass());
		assertNotNull(ReflectionUtils.getPrivateMember(node, "filterExpression"));
	}
	
	public void testTagTokenReturnsTemplateNode() throws Exception {
		TemplateTagParser mockTagParser = new TemplateTagParser() {
			public TemplateNode parse(Parser parser, Token token) {
				final String textOuter = token.getContents();
				return new TemplateNode() {
					@SuppressWarnings("unused")
					private String text = textOuter;
					public String render(Context context) {
						return null;
					}
				};
			}
		};
		
		token = new Token(TokenType.TAG, "tag");
		tagLibrary.register("tag", mockTagParser);
		node = tagCompiler.compile(token, parser);
		
		assertEquals("tag", ReflectionUtils.getPrivateMember(node, "text"));
	}
	
}
