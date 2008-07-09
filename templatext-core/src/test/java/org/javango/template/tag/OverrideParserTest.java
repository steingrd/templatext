package org.javango.template.tag;

import java.util.Arrays;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.compiler.Token;
import org.javango.template.compiler.TokenStream;
import org.javango.template.compiler.TokenType;
import org.javango.template.core.NodeList;
import org.javango.template.core.TextNode;
import org.javango.template.test.ReflectionUtils;

import junit.framework.TestCase;

public class OverrideParserTest extends TestCase {

	private OverrideParser tagParser;
	private Parser parser;
	private Token token;
	private TemplateNode node;
	
	@Override
	protected void setUp() throws Exception {
		tagParser = new OverrideParser();
	}
	
	public void testWithValidNameReturnsOverrideNode() throws Exception {
		// mock parser that returns a TextNode before the endoverride node.
		parser = new Parser() {
			public NodeList parse(String[] until) {
				NodeList nodeList = new NodeList();
				nodeList.add(new TextNode("CHILD"));
				return nodeList;
			}
			public NodeList parse(String until) {
				return parse(new String[]{until});
			}
			public TokenStream getTokenStream() {
				return new TokenStream(Arrays.asList(new Token[] { new Token(TokenType.TAG, "endoverride") }));
			}
		};
		
		token = new Token(TokenType.TAG, "override sidebar");
		node = tagParser.parse(parser, token);
		
		assertEquals(OverrideNode.class, node.getClass());
		assertEquals("sidebar", ReflectionUtils.getPrivateMember(node, "name"));
		assertNotNull(ReflectionUtils.getPrivateMember(node, "block"));
	}
	
	public void testWithoutNameThrowsException() throws Exception {
		// mock parser that returns a TextNode before end of stream
		parser = new Parser() {
			public NodeList parse(String[] until) {
				NodeList nodeList = new NodeList();
				nodeList.add(new TextNode("CHILD"));
				return nodeList;
			}
			public NodeList parse(String until) {
				return parse(new String[]{until});
			}
			public TokenStream getTokenStream() {
				return new TokenStream(Arrays.asList(new Token[] { }));
			}
		};
		token = new Token(TokenType.TAG, "override");
		try {
			node = tagParser.parse(parser, token);
			fail("expected exception");
		} catch (TemplateException e) {
		}
	}
}
