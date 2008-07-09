package org.javango.template.tag;

import org.javango.template.Context;
import org.javango.template.Template;
import org.javango.template.TemplateException;
import org.javango.template.TemplateLoader;
import org.javango.template.TemplateNotFoundException;
import org.javango.template.core.NodeList;
import org.javango.template.core.TextNode;

import junit.framework.TestCase;

public class InsertNodeTest extends TestCase {

	private InsertNode node;
	private Context context;
	private TemplateLoader mockLoader;
	
	@Override
	protected void setUp() throws Exception {
		context = new Context();
		mockLoader = new TemplateLoader() {
			public Template load(String name) {
				if (!name.equals("inner")) {
					throw new TemplateNotFoundException("not inner");
				}
				NodeList nodes = new NodeList();
				nodes.add(new TextNode("INNER"));
				return new Template(nodes);
			}
		};
		context.configuration().setTemplateLoader(mockLoader);
	}
	
	public void testWithValidTemplateNameInsertsTemplate() throws Exception {
		node = new InsertNode("inner");
		String result = node.render(context);
		assertEquals("INNER", result);
	}
	
	public void testMissingTemplateNameThrowsException() throws Exception {
		node = new InsertNode("");
		try {
			node.render(context);
			fail("expected exception");
		} catch (TemplateException e) {
		}
	}
	
	public void testInvalidTemplateNameThrowsException() throws Exception {
		node = new InsertNode("notinner");
		try {
			node.render(context);
			fail("expected exception");
		} catch (TemplateException e) {
		}
	}
	
}
