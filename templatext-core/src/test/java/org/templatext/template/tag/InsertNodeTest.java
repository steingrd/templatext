package org.templatext.template.tag;

import java.util.HashMap;

import org.templatext.template.Configuration;
import org.templatext.template.Context;
import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;
import org.templatext.template.TemplateNotFoundException;
import org.templatext.template.core.NodeList;
import org.templatext.template.core.TextNode;
import org.templatext.template.tag.InsertNode;

import junit.framework.TestCase;

public class InsertNodeTest extends TestCase {

	private InsertNode node;
	private Context context;
	private TemplateLoader mockLoader;
	
	@Override
	protected void setUp() throws Exception {
		context = Context.create();
		mockLoader = new TemplateLoader() {
			public Template load(String name) throws TemplateNotFoundException {
				if (!name.equals("inner")) {
					throw new TemplateNotFoundException("not inner");
				}
				NodeList nodes = new NodeList();
				nodes.add(new TextNode("INNER"));
				return new Template(nodes);
			}
		};
		
		Configuration configuration = new Configuration();
		configuration.setTemplateLoader(mockLoader);
		
		context = Context.create(configuration, null, new HashMap<String, Object>());
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
