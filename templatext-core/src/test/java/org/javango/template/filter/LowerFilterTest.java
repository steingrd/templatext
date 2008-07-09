package org.javango.template.filter;

import junit.framework.TestCase;

import org.javango.template.TemplateFilter;

public class LowerFilterTest extends TestCase {

	private TemplateFilter filter;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		filter = new LowerFilter();
	}
	
	public void testLowerCaseStringStaysLowerCase() throws Exception {
		assertEquals("lowercase", filter.filter("lowercase", null));
	}
	
	public void testUpperCaseStringBecomesLowerCase() throws Exception {
		assertEquals("uppercase", filter.filter("UPPERCASE", null));
	}
	
	public void testNonStringObjectRendersEmpty() throws Exception {	
		assertEquals("", filter.filter(new Integer(42), null));
	}
	
}
