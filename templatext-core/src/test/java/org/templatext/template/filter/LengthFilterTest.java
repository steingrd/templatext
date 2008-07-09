package org.templatext.template.filter;

import java.util.ArrayList;
import java.util.List;

import org.templatext.template.TemplateFilter;
import org.templatext.template.filter.LengthFilter;

import junit.framework.TestCase;

public class LengthFilterTest extends TestCase {

	private TemplateFilter filter;
	
	@Override
	protected void setUp() throws Exception {
		filter = new LengthFilter();
	}
	
	public void testLengthFilterAcceptStringAndReturnsLength() throws Exception {
		String mystr = "Hello, world!";
		assertEquals(mystr.length(), filter.filter(mystr, null));
	}
	
	public void testLengthFilterAcceptsArrayAndReturnsLength() throws Exception {
		int[] myarray = new int[] { 1,2,3,4,5 };
		assertEquals(5, filter.filter(myarray, null));
	}
	
	public void testLengthFilterAcceptsCollectionAndReturnsLength() throws Exception {
		List<String> mylist = new ArrayList<String>();
		mylist.add("1");
		mylist.add("2");
		mylist.add("3");
		
		assertEquals(3, filter.filter(mylist, null));
	}
	
	public void testLengthFilterReturnsZeroOnInvalidInput() throws Exception {
		assertEquals(0, filter.filter(new Integer(42), null));
	}
	
}
