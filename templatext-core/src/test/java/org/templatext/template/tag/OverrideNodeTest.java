package org.templatext.template.tag;

import org.templatext.template.Context;
import org.templatext.template.core.NodeList;
import org.templatext.template.core.TextNode;
import org.templatext.template.tag.OverrideNode;

import junit.framework.TestCase;

public class OverrideNodeTest extends TestCase {
	
	private OverrideNode node;
	private Context context;
	private NodeList block1;
	private NodeList block2;
	
	@Override
	protected void setUp() throws Exception {
		context = Context.create();
		block1 = new NodeList();
		block1.add(new TextNode("test1"));
		block2 = new NodeList();
		block2.add(new TextNode("test2"));
	}
	
	public void testNodeWithouthOverrideRendersItself() throws Exception {
		node = new OverrideNode("alone", block1);
		assertEquals("test1", node.render(context));
	}
	
	public void testNodeWithOverrideRendersTheOverride() throws Exception {
		node = new OverrideNode("string", block1);
		context.put("__override_string", block2);
		assertEquals("test2", node.render(context));
	}
	
}
