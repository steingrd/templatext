package org.templatext.template.tag;

import org.templatext.template.Parser;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.compiler.Token;
import org.templatext.template.compiler.TokenType;
import org.templatext.template.tag.InheritNode;
import org.templatext.template.tag.InheritParser;
import org.templatext.template.test.ReflectionUtils;

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
