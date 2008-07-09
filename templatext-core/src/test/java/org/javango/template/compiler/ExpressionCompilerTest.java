package org.javango.template.compiler;

import org.javango.template.TemplateException;
import org.javango.template.TemplateFilter;
import org.javango.template.core.FilterExpression;
import org.javango.template.filter.CutFilter;

import junit.framework.TestCase;

public class ExpressionCompilerTest extends TestCase {

	private ExpressionCompiler compiler; 
	private FilterExpression expression;
	private FilterLibrary library;
	private TemplateFilter mockTemplateFilter;
	
	@Override
	protected void setUp() throws Exception {
		mockTemplateFilter = new TemplateFilter() {
			public Object filter(Object object, String arg) {
				return object;
			}
		};
		
		library = new FilterLibrary();
		library.register("upper", mockTemplateFilter);
		library.register("lower", mockTemplateFilter);
		library.register("cut", new CutFilter());
		
		compiler = new ExpressionCompiler(library);
	}
	
	public void testVariableOnly() throws Exception {
		expression = compiler.compile("myvariable");
		assertEquals("myvariable", expression.getVariable().getName());
		assertNull(expression.getChain());
	}
	
	public void testVariableWithPropertyThenFilter() throws Exception {
		expression = compiler.compile("myvariable.property|upper");
		assertEquals("myvariable", expression.getVariable().getName());
		assertEquals(1, expression.getChain().size());
	}
	
	public void testVariableWithSingleValidFilter() throws Exception {
		expression = compiler.compile("myvariable|upper");
		assertEquals("myvariable", expression.getVariable().getName());
		assertEquals(1, expression.getChain().size());
	}
	
	public void testVariableWithMultipleValidFilters() throws Exception {
		expression = compiler.compile("myvariable|upper|lower");
		assertEquals("myvariable", expression.getVariable().getName());
		assertEquals(2, expression.getChain().size());
	}
	
	public void testVariableWithInvalidFilter() throws Exception {
		try {
			expression = compiler.compile("myvariable|upper|lover");
			fail("expected TemplateSyntaxErrorException");
		} catch (TemplateException e) {
		}
	}
	
	public void testVariableWithValidFilterWithEscapedArguments() throws Exception {
		expression = compiler.compile("myvariable|cut:' '");
		assertEquals("myvariable", expression.getVariable().getName());
		assertEquals(1, expression.getChain().size());
	}
	
	public void testVariableWithValidFilterWithArguments() throws Exception {
		expression = compiler.compile("myvariable|cut:a");
		assertEquals("myvariable", expression.getVariable().getName());
		assertEquals(1, expression.getChain().size());
	}
	
	public void testVariableWithValidFilterChainWithEscapedAndNonEscapedArguments() throws Exception {
		expression = compiler.compile("myvariable|cut:' '|cut:a|upper");
		assertEquals("myvariable", expression.getVariable().getName());
		assertEquals(3, expression.getChain().size());
	}
}
