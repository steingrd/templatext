package org.javango.template.core;

import org.javango.template.Context;

import junit.framework.TestCase;

public class NodeListTest extends TestCase {

	private NodeList nodeList;
	private Context context;
	private String result;
	
	@Override
	protected void setUp() throws Exception {
		context = new Context();
	}
	
	public void testEmptyListRendersEmptyString() throws Exception {
		nodeList = new NodeList();
		result = nodeList.render(context);
		assertEquals("", result);
	}
	
	public void testOneElementListRendersElement() throws Exception {
		nodeList = new NodeList();
		nodeList.add(new TextNode("a"));
		result = nodeList.render(context);
		assertEquals("a", result);
	}
	
	public void testThreeElementsListRendersElementsInOrder() throws Exception {
		nodeList = new NodeList();
		nodeList.add(new TextNode("a"));
		nodeList.add(new TextNode("b"));
		nodeList.add(new TextNode("c"));
		result = nodeList.render(context);
		assertEquals("abc", result);
	}
	
}
