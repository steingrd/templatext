package org.javango.template.filter;

import org.javango.template.TemplateFilter;
import org.javango.template.TemplateSyntaxErrorException;

import junit.framework.TestCase;

public class DefaultFilterTest extends TestCase {

	private TemplateFilter filter;
	
	@Override
	protected void setUp() throws Exception {
		filter = new DefaultFilter();
	}
	
	public void testTrueValueEvaluatesToItself() throws Exception {
		assertEquals("something", filter.filter("something", "nothing"));
	}
	
	public void testFalseValueEvalutesToArgument() throws Exception {
		assertEquals("nothing", filter.filter("", "nothing"));
	}
	
	public void testMissingArgumentThrowsException() throws Exception {
		try {
			filter.filter("value", null);
			fail("expected TemplateSyntaxErrorException");
		} catch (TemplateSyntaxErrorException e) {
		}
	}
	
}
