package org.javango.template.core;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.javango.template.Context;
import org.javango.template.compiler.ExpressionCompiler;
import org.javango.template.compiler.FilterLibrary;

public class VariableNodeTest extends TestCase {

	private VariableNode variableNode;
	private Context context;
	private FilterLibrary library;
	
	@Override
	protected void setUp() throws Exception {
		this.context = new Context();
		this.library = new FilterLibrary();
	}
	
	public void testStringVariableRendersAsString() throws Exception {
		context.put("variable1", "value 1");
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("variable1"));
		assertEquals("value 1", variableNode.render(context));
	}
	
	public void testObjectVariableRendersAsString() throws Exception {
		context.put("variable1", new Integer(42));
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("variable1"));
		assertEquals("42", variableNode.render(context));
	}
	
	public void testUnknownVariableRendersAsEmpty() throws Exception {
		assertFalse(context.contains("variable1"));
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("variable1"));
		assertEquals("", variableNode.render(context));
	}
	
	public void testEmptyVariableRendersAsEmpty() throws Exception {
		variableNode = new VariableNode(new ExpressionCompiler(library).compile(""));
		assertEquals("", variableNode.render(context));
	}
	
	public void testVariableDotMemberRendersTheMemberValue() throws Exception {	
		context.put("object", new Mock());
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("object.member"));
		assertEquals(new Mock().member, variableNode.render(context));
	}
	
	public void testArrayDotLengthRendersTheArrayLength() throws Exception {
		String[] array = new String[] { "first", "second", "third" };
		context.put("array", array);
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("array.length"));
		assertEquals("3", variableNode.render(context));
	}
	
	public void testVariableDotPropertyRendersThePropertyValue() throws Exception {
		context.put("class", String.class);
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("class.name"));
		assertEquals("java.lang.String", variableNode.render(context));
	}
	
	public void testVariableDotMethodRendersTheMethodReturnValue() throws Exception {
		context.put("string", "a string");
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("string.length"));
		assertEquals("8", variableNode.render(context));
	}
	
	public void testVariableDotIntegerRendersTheIndexedValue() throws Exception {
		context.put("array", new String[] { "first", "second", "third" });
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("array.2"));
		assertEquals("third", variableNode.render(context));
	}
	
	public void testVariableDotKeyRendersTheMappedKeyValue() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mykey", "myvalue");
		context.put("mymap", map);
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("mymap.mykey"));
		assertEquals("myvalue", variableNode.render(context));
		
	}
	
	public void testVariableThroughFilterWithArgumentRendersOk() throws Exception {
		context.put("mystr", "String with spaces");
		variableNode = new VariableNode(new ExpressionCompiler(library).compile("mystr|cut:' '|upper"));
		assertEquals("STRINGWITHSPACES", variableNode.render(context));
	}
	
	public static class Mock {
		public String member = "!value!";
	}
}
