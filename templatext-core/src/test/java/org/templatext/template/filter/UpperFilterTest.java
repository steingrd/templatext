package org.templatext.template.filter;

import junit.framework.TestCase;

import org.templatext.template.TemplateFilter;
import org.templatext.template.filter.UpperFilter;

public class UpperFilterTest extends TestCase {

	private TemplateFilter filter;
	
	@Override
	protected void setUp() throws Exception {
		filter = new UpperFilter();
	}
	
	public void testLowerCaseStringBecomesUpperCase() throws Exception {
		assertEquals("UPPERCASE", filter.filter("uppercase", null));
	}
	
	public void testUpperCaseStringStaysUpperCase() throws Exception {
		assertEquals("UPPERCASE", filter.filter("UPPERCASE", null));
	}
	
	public void testNonStringObjectRendersEmpty() throws Exception {
		assertEquals("", filter.filter(new Integer(42), null));
	}
}
