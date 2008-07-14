package org.templatext.template.tag;

import junit.framework.TestCase;

import org.templatext.template.Context;
import org.templatext.template.core.NodeList;
import org.templatext.template.core.TextNode;
import org.templatext.template.tag.IfNode;

public class IfNodeTest extends TestCase {

	private IfNode ifNode;
	private Context context;
	
	private NodeList trueBlock;
	private NodeList elseBlock;
	
	@Override
	protected void setUp() throws Exception {
		context = Context.create();
		
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
