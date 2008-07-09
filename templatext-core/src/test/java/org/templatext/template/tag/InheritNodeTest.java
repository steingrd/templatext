package org.templatext.template.tag;

import org.templatext.template.Context;
import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;
import org.templatext.template.core.NodeList;
import org.templatext.template.core.TextNode;
import org.templatext.template.tag.InheritNode;
import org.templatext.template.tag.OverrideNode;

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