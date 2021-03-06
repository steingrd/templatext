package org.templatext.template.compiler;

import junit.framework.TestCase;

import org.templatext.template.Context;
import org.templatext.template.compiler.FilterLibrary;
import org.templatext.template.compiler.ParserImpl;
import org.templatext.template.compiler.TagLibrary;
import org.templatext.template.core.NodeList;

public class ParserTest extends TestCase {

	private Context context;
	private TagLibrary tagLibrary;
	private FilterLibrary filterLibrary;
	
	@Override
	protected void setUp() throws Exception {
		tagLibrary = new TagLibrary();
		filterLibrary = new FilterLibrary();
		context = Context.create();
	}
	
	public void testTextOnly() throws Exception {
		NodeList block = new ParserImpl("this is text only, should not fail", tagLibrary, filterLibrary).parse();
		assertEquals("this is text only, should not fail", block.render(context));
	}
	
	public void testTextWithIfStatement() throws Exception {
		context.put("myvar", false);
		NodeList block = new ParserImpl("some text{% if myvar %}foo!{% endif %} more text", tagLibrary, filterLibrary).parse();
		assertEquals("some text more text", block.render(context));
	}
	
	public void testTextWithForStatement() throws Exception {
		context.put("mylist", new String[] { "1", "2", "3" });
		NodeList block = new ParserImpl("some text {% for v in mylist %}I{% endfor %} more text", tagLibrary, filterLibrary).parse();
		assertEquals("some text III more text", block.render(context));
	}
	
	public void testTextWithVariableStatement() throws Exception {
		context.put("myvar", "FOO");
		NodeList block = new ParserImpl("some text {{ myvar }} more text", tagLibrary, filterLibrary).parse();
		assertEquals("some text FOO more text", block.render(context));
	}
	
	public void testTextWithEmptyVariableStatement() throws Exception {
		NodeList block = new ParserImpl("some {{ }}text", tagLibrary, filterLibrary).parse();
		assertEquals("some text", block.render(context));
	}
	
	public void testForStatementWithVariableBody() throws Exception {
		context.put("mylist", new String[] { "1", "2", "3" });
		NodeList block = new ParserImpl("some text {% for v in mylist %}{{ v }}{% endfor %} more text", tagLibrary, filterLibrary).parse();
		assertEquals("some text 123 more text", block.render(context));
	}
	
	public void testTwoVariablesSeparatedWithNewlines() throws Exception {
		context.put("var1", "foo");
		context.put("var2", "bar");
		NodeList block = new ParserImpl("\n\n{{ var1 }}\n\n{{ var2 }}\n\n", tagLibrary, filterLibrary).parse();
		assertEquals("\n\nfoo\n\nbar\n\n", block.render(context));
	}
	
}
