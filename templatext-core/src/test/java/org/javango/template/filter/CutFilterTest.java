package org.javango.template.filter;

import org.javango.template.TemplateFilter;

import junit.framework.TestCase;

public class CutFilterTest extends TestCase {

	private TemplateFilter filter;
	
	protected void setUp() throws Exception {
		filter = new CutFilter();
	}
	
	public void testTextContainsArgument() throws Exception {
		assertEquals("Stringwithspaces", filter.filter("String with spaces", " "));
	}
	
	public void testTextDoesNotContainArgument() throws Exception {
		assertEquals("String with spaces", filter.filter("String with spaces", "\n"));
	}

}
