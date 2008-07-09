package org.javango.template.tag;

import org.javango.template.Context;
import org.javango.template.Template;
import org.javango.template.TemplateException;
import org.javango.template.TemplateLoader;
import org.javango.template.core.NodeList;
import org.javango.template.core.TextNode;

import junit.framework.TestCase;

public class InheritNodeTest extends TestCase {

	private InheritNode node;
	private Context context;
	private Template current;
	private Template parent;
	private NodeList currentNodes;
	private NodeList parentNodes;
	private TemplateLoader templateLoader;
	
	@Override
	protected void setUp() throws Exception {
		NodeList tmp;
		
		templateLoader = new MockTemplateLoader();
		
		currentNodes = new NodeList();
		current = new Template(currentNodes);
		parentNodes = new NodeList();
		tmp = new NodeList();
		tmp.add(new TextNode("PARENT"));
		parentNodes.add(new TextNode("PARENT"));
		parentNodes.add(new OverrideNode("middle", tmp));
		parentNodes.add(new TextNode("PARENT"));
		parent = new Template(parentNodes);
		
		context = new Context();
		context.configuration().setTemplateLoader(templateLoader);
		context.put("__template", current);
	}
	
	public void testInheritWithoutOverridesRendersParent() throws Exception {
		node = new InheritNode("parent");
		assertEquals(current, context.template());
		assertEquals("PARENTPARENTPARENT", node.render(context));
	}
	
	public void testInheritWithOverridesRendersTheChild() throws Exception {
		node = new InheritNode("parent");
		NodeList tmp = new NodeList();
		tmp.add(new TextNode("CHILD"));
		currentNodes.add(new OverrideNode("middle", tmp));
		
		assertEquals(current, context.template());
		assertEquals("PARENTCHILDPARENT", node.render(context));
	}
	
	class MockTemplateLoader implements TemplateLoader {
		public Template load(String name) throws TemplateException {
			if (name.equals("parent")) {
				return parent;
			}
			throw new TemplateException("invalid templatename");
		}
	}
	
}
