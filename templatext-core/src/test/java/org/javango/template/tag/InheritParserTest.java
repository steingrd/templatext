package org.javango.template.tag;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.compiler.Token;
import org.javango.template.compiler.TokenType;
import org.javango.template.test.ReflectionUtils;

import junit.framework.TestCase;

public class InheritParserTest extends TestCase {

	private InheritParser tagParser;
	private Parser parser;
	private Token token;
	private TemplateNode node;
	
	@Override
	protected void setUp() throws Exception {
		tagParser = new InheritParser();
	}
	
	public void testValidArgumentsReturnsInheritNode() throws Exception {
		token = new Token(TokenType.TAG, "inherit filename.html");
		node = tagParser.parse(parser, token);
		
		assertEquals(InheritNode.class, node.getClass());
		assertEquals("filename.html", ReflectionUtils.getPrivateMember(node, "parentTemplateName"));
	}
	
	public void testMissingArgumentThrowsException() throws Exception {
		token = new Token(TokenType.TAG, "inherit");
		
		try {
			node = tagParser.parse(parser, token);
			fail("expected exception");
		} catch (TemplateException e) {
		}
	}
	
}
