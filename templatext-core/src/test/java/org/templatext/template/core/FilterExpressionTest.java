package org.templatext.template.core;

import junit.framework.TestCase;

import org.templatext.template.Context;
import org.templatext.template.TemplateFilter;
import org.templatext.template.core.FilterChain;
import org.templatext.template.core.FilterExpression;

public class FilterExpressionTest extends TestCase {

	private FilterExpression expression;
	private Context context;
	private FilterChain chain;
	private TemplateFilter mockIdentityFilter;
	private TemplateFilter mockIncreaseFilter;
	
	@Override
	protected void setUp() throws Exception {
		context = new Context();
		chain = new FilterChain();
		
		// this identity filter simply returns its argument
		mockIdentityFilter = new TemplateFilter() {
			public Object filter(Object object, String arg) {
				return object;
			}
		};
		
		// this filter expects a Integer object and returns
		// it added with one
		mockIncreaseFilter = new TemplateFilter() {
			public Object filter(Object object, String arg) {
				Integer integer = (Integer)object;
				return new Integer(integer + 1);
			}
		};
	}
	
	public void testEvaluateVariableOnly() throws Exception {
		expression = new FilterExpression("myvariable");
		Object object = new Object();
		context.put("myvariable", object);
		
		assertEquals(object, expression.evaluate(context));
	}
	
	public void testEvaluateVariableWithFilter() throws Exception {
		chain.add(mockIncreaseFilter, null);
		expression = new FilterExpression("myvariable", chain);
		
		Integer object = new Integer(23);
		context.put("myvariable", object);
		
		assertEquals(24, expression.evaluate(context));
	}

	public void testEvaluateVariableWithFilterChain() throws Exception {
		chain.add(mockIncreaseFilter, null);
		chain.add(mockIdentityFilter, null);
		chain.add(mockIncreaseFilter, null);
		expression = new FilterExpression("myvariable", chain);
		
		Integer object = new Integer(23);
		context.put("myvariable", object);
		
		assertEquals(25, expression.evaluate(context));
	}
	
	public void testEvaluateVariableWithFilterAndTypeMismatch() throws Exception {
		chain.add(mockIncreaseFilter, null);
		expression = new FilterExpression("myvariable", chain);
		
		String object = "not an integer";
		context.put("myvariable", object);
		
		try {
			expression.evaluate(context);
			fail("expected ClassCastException");
		} catch (ClassCastException e) {
		}
	}
	
	public void testEvaluateFilterChainWithTypeMismatch() throws Exception {
		chain.add(mockIdentityFilter, null);
		chain.add(mockIncreaseFilter, null);
		expression = new FilterExpression("myvariable", chain);
		
		String object = "not an integer";
		context.put("myvariable", object);
		
		try {
			expression.evaluate(context);
			fail("expected ClassCastException");
		} catch (ClassCastException e) {
		}
	}
}
