package org.templatext.template.tag;

import java.util.Arrays;

import org.templatext.template.Parser;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.compiler.Token;
import org.templatext.template.compiler.TokenStream;
import org.templatext.template.compiler.TokenType;
import org.templatext.template.core.NodeList;
import org.templatext.template.core.TextNode;
import org.templatext.template.tag.OverrideNode;
import org.templatext.template.tag.OverrideParser;
import org.templatext.template.test.ReflectionUtils;

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
