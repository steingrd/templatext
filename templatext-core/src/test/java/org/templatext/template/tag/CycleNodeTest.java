package org.templatext.template.tag;

import org.templatext.template.Context;
import org.templatext.template.TemplateException;
import org.templatext.template.tag.CycleNode;

import junit.framework.TestCase;

public class CycleNodeTest extends TestCase {

	private CycleNode node;
	private Context context;

	@Override
	protected void setUp() throws Exception {
		context = new Context();
		context.put("forloop", new ForLoopContextObjectWithSideEffect());
	}

	public void testCycleSingleItem() throws Exception {
		node = new CycleNode(new String[] { "odd" });

		assertEquals("odd", node.render(context));
		assertEquals("odd", node.render(context));
		assertEquals("odd", node.render(context));
		assertEquals("odd", node.render(context));
		assertEquals("odd", node.render(context));
	}

	public void testCycleTwoItems() throws Exception {
		node = new CycleNode(new String[] { "odd", "even" });

		assertEquals("odd", node.render(context));
		assertEquals("even", node.render(context));
		assertEquals("odd", node.render(context));
		assertEquals("even", node.render(context));
		assertEquals("odd", node.render(context));
	}

	public void testCycleFiveItems() throws Exception {
		node = new CycleNode(new String[] { "one", "two", "three", "four", "five" });

		assertEquals("one", node.render(context));
		assertEquals("two", node.render(context));
		assertEquals("three", node.render(context));
		assertEquals("four", node.render(context));
		assertEquals("five", node.render(context));
		assertEquals("one", node.render(context));
	}

	public void testNotInsideForLoopThrowsException() throws Exception {
		context.remove("forloop");
		node = new CycleNode(new String[] { "odd", "even" });
		
		try {
			node.render(context);
			fail("expected TemplateException");
		} catch (TemplateException e) {
		}
	}
	
	public static class ForLoopContextObjectWithSideEffect {
		private int counter0;

		public int getCounter0() {
			return counter0++;
		}
	}

}
