package org.javango.template.tag;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.javango.template.Parser;
import org.javango.template.TemplateNode;
import org.javango.template.compiler.Token;
import org.javango.template.compiler.TokenStream;
import org.javango.template.compiler.TokenType;
import org.javango.template.core.NodeList;

public class CycleParserTest extends TestCase {

	private CycleParser tagParser;
	private TemplateNode node;
	private Token token;
	private Parser parser;
	
	@Override
	protected void setUp() throws Exception {
		parser = new Parser() {		
			public NodeList parse(String[] until) {
				return null;
			}
			public NodeList parse(String until) {
				return null;
			}
			public TokenStream getTokenStream() {
				return null;
			}
		};
		tagParser = new CycleParser();
	}
	
	public void testSingleArgument() throws Exception {
		token = new Token(TokenType.TAG, "cycle single"); 
		node = tagParser.parse(parser, token);
		assertEquals(CycleNode.class, node.getClass());
		assertEquals(1, retrieveElementsArray(node).length);
	}

	public void testTwoArguments() throws Exception {
		token = new Token(TokenType.TAG, "cycle odd even"); 
		node = tagParser.parse(parser, token);
		assertEquals(CycleNode.class, node.getClass());
		assertEquals(2, retrieveElementsArray(node).length);
	}
	
	public void testFiveArguments() throws Exception {
		token = new Token(TokenType.TAG, "cycle one two three four five"); 
		node = tagParser.parse(parser, token);
		assertEquals(CycleNode.class, node.getClass());
		assertEquals(5, retrieveElementsArray(node).length);
	}
	
	@SuppressWarnings("unchecked")
	private String[] retrieveElementsArray(TemplateNode node) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Class klass = node.getClass();
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("elements")) {
				field.setAccessible(true);
				return (String[]) field.get(node);
			}
		}
		return null;
	}
}
