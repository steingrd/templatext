package org.templatext.template.filter;

import junit.framework.TestCase;

import org.templatext.template.TemplateFilter;
import org.templatext.template.filter.LowerFilter;

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
