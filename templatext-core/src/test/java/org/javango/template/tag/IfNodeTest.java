package org.javango.template.tag;

import junit.framework.TestCase;

import org.javango.template.Context;
import org.javango.template.core.NodeList;
import org.javango.template.core.TextNode;
import org.javango.template.tag.IfNode;

public class IfNodeTest extends TestCase {

	private IfNode ifNode;
	private Context context;
	
	private NodeList trueBlock;
	private NodeList elseBlock;
	
	@Override
	protected void setUp() throws Exception {
		context = new Context();
		
		// create mock true and else blocks, use these to check which block
		// executed, by checking the rendered output.
		trueBlock = new NodeList();
		trueBlock.add(new TextNode("true"));
		elseBlock = new NodeList();
		elseBlock.add(new TextNode("else"));
	}
	
	public void testBooleanIsTrue() throws Exception {
		context.put("myvar", new Boolean(true));
		ifNode = new IfNode("myvar", trueBlock, elseBlock);
		
		assertEquals("true", ifNode.render(context));
	}
	
	public void testBooleanIsFalse() throws Exception {
		context.put("myvar", new Boolean(false));
		ifNode = new IfNode("myvar", trueBlock, elseBlock);
		
		assertEquals("else", ifNode.render(context));
	}
	
	public void testPropertyIsTrue() throws Exception {
		context.put("myvar", new Mock());
		ifNode = new IfNode("myvar.value", trueBlock, elseBlock);
		
		assertEquals("true", ifNode.render(context));
	}
	
	public static class Mock {
		public boolean getValue() {
			return true;
		}
	}
}
