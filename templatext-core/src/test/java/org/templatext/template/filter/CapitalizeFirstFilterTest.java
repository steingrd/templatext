package org.templatext.template.filter;

import org.templatext.template.TemplateFilter;
import org.templatext.template.filter.CapitalizeFirstFilter;

import junit.framework.TestCase;

public class CapitalizeFirstFilterTest extends TestCase {

	private TemplateFilter filter;
	
	protected void setUp() throws Exception {
		filter = new CapitalizeFirstFilter();
	}
	
	public void testLowercaseStringGetsUppercaseFirst() throws Exception {
		assertEquals("Hello", filter.filter("hello", null));
	}
	
	public void testUppercaseStringKeepsUppercaseFirst() throws Exception {
		assertEquals("HELLO", filter.filter("HELLO", null));
	}

}
