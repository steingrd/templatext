package org.javango.template.tag;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.javango.template.Context;
import org.javango.template.TemplateException;
import org.javango.template.core.NodeList;
import org.javango.template.core.FilterExpression;
import org.javango.template.core.TextNode;
import org.javango.template.core.VariableNode;
import org.javango.template.tag.ForNode;
import org.javango.template.tag.IfNode;

public class ForNodeTest extends TestCase {

	private ForNode forNode;
	private NodeList body;
	private Context context;
	
	@Override
	protected void setUp() throws Exception {
		context = new Context();
		body = new NodeList();
	}
	
	public void testIterateOverIterable() throws Exception {
		List<String> mylist = new ArrayList<String>();
		mylist.add("1"); 
		mylist.add("2"); 
		mylist.add("3"); 
		mylist.add("4"); 
		mylist.add("5");
		context.put("mylist", mylist);
	
		forNode = new ForNode("var", "mylist", body);
		body.add(new TextNode("I"));
		
		assertEquals("IIIII", forNode.render(context));
	}
	
	public void testIterateOverIterator() throws Exception {
		List<String> mylist = new ArrayList<String>();
		mylist.add("1"); 
		mylist.add("2"); 
		mylist.add("3"); 
		mylist.add("4"); 
		mylist.add("5");
		context.put("mylist", mylist.iterator());
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new VariableNode(new FilterExpression("var")));
		
		assertEquals("12345", forNode.render(context));
	}
	
	public void testIterateOverArray() throws Exception {
		context.put("mylist", new String[] { "1", "2", "3", "4", "5" });
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new VariableNode(new FilterExpression("var")));
		
		assertEquals("12345", forNode.render(context));
	}
	
	public void testIterateOverPropertyArray() throws Exception {
		context.put("myobject", new Mock());
		
		forNode = new ForNode("var", "myobject.value", body);
		body.add(new VariableNode(new FilterExpression("var")));
		
		assertEquals("onetwothree", forNode.render(context));
	}
	
	public void testIterateOverArrayOutputForLoopCounter() throws Exception {
		context.put("mylist", new String[] { "one", "two", "three" });
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new VariableNode(new FilterExpression("forloop.counter")));
		
		assertEquals("123", forNode.render(context));
	}
	
	public void testIterateOverArrayOutputForLoopCounter0() throws Exception {
		context.put("mylist", new String[] { "one", "two", "three" });
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new VariableNode(new FilterExpression("forloop.counter0")));
		
		assertEquals("012", forNode.render(context));
	}
	
	public void testIterateOverArrayCheckFirstLoopVariable() throws Exception {
		context.put("mylist", new String[] {"one", "two", "three", "four", "five"});
		NodeList trueBlock = new NodeList();
		trueBlock.add(new VariableNode(new FilterExpression("var")));
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new IfNode("forloop.first", trueBlock, null));
		
		assertEquals("one", forNode.render(context));
	}
	
	public void testIterateOverArrayCheckLastLoopVariable() throws Exception {
		context.put("mylist", new String[] {"one", "two", "three", "four", "five"});
		NodeList trueBlock = new NodeList();
		trueBlock.add(new VariableNode(new FilterExpression("var")));
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new IfNode("forloop.last", trueBlock, null));
		
		assertEquals("five", forNode.render(context));
	}
	
//	public void testIterateOverSingleItemCheckLastAndFirstLoopVariable() throws Exception {
//		context.put("myarray", new String[] {"one"});
//		BlockNode block = new BlockNode();
//		block.add(new VariableNode(new FilterExpression("forloop.first")));
//		block.add(new VariableNode(new FilterExpression("forloop.last")));
//		forNodeBody.add(block);
//		forNode.setBody(forNodeBody);
//		forNode.setLoopVariableName("var");
//		forNode.setSequenceVariableName("myarray");
//		
//		assertEquals("oneone", forNode.render(context));
//	}
	
	public void testTryIterateOverNonIterable() throws Exception {
		context.put("mylist", new Integer(42));
		
		forNode = new ForNode("var", "mylist", body);
		body.add(new VariableNode(new FilterExpression("var")));
		
		try {
			forNode.render(context);
			fail("expected TemplateException");
		} catch (TemplateException e) {
		}
	}
	
	public static class Mock {
		public String[] getValue() {
			return new String[] { "one", "two", "three" };
		}
	}
}
