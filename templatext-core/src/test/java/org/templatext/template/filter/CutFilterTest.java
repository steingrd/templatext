package org.templatext.template.filter;

import org.templatext.template.TemplateFilter;
import org.templatext.template.filter.CutFilter;

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
