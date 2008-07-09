package org.javango.template.core;

import junit.framework.TestCase;

import org.javango.template.Context;

public class TextNodeTest extends TestCase {

	private TextNode textNode;

	private Context context;

	@Override
	protected void setUp() throws Exception {
		context = new Context();
	}

	public void testNullStringRendersAsEmpty() throws Exception {
		textNode = new TextNode(null);
		assertEquals("", textNode.render(context));
	}
	
	public void testEmptyStringRendersAsEmpty() throws Exception {
		textNode = new TextNode("");
		assertEquals("", textNode.render(context));
	}
	
	public void testSpacesRendersAsSpaces() throws Exception {
		textNode = new TextNode(" \t\n");
		assertEquals(" \t\n", textNode.render(context));
	}

	public void testTextRendersAsItself() throws Exception {
		String textString = "this is a sample text";
		textNode = new TextNode(textString);
		assertEquals(textString, textNode.render(context));
	}

}
