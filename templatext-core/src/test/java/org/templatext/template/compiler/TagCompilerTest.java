package org.templatext.template.compiler;

import org.templatext.template.Context;
import org.templatext.template.Parser;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateTagParser;
import org.templatext.template.compiler.FilterLibrary;
import org.templatext.template.compiler.TagCompiler;
import org.templatext.template.compiler.TagLibrary;
import org.templatext.template.compiler.Token;
import org.templatext.template.compiler.TokenType;
import org.templatext.template.core.TextNode;
import org.templatext.template.core.VariableNode;
import org.templatext.template.test.ReflectionUtils;

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
