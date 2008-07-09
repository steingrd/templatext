package org.templatext.template.utils;

import org.templatext.template.utils.ObjectTruthValue;

import junit.framework.TestCase;

public class ObjectTruthValueTest extends TestCase {
	
	public void testBoolean() throws Exception {
		assertFalse(ObjectTruthValue.evaluate(null));
		assertFalse(ObjectTruthValue.evaluate(false));
		assertTrue(ObjectTruthValue.evaluate(true));
	}
	
	public void testString() throws Exception {
		assertFalse(ObjectTruthValue.evaluate(null));
		assertFalse(ObjectTruthValue.evaluate(""));
		assertTrue(ObjectTruthValue.evaluate("something"));
	}
	
	public void testInteger() throws Exception {
		assertFalse(ObjectTruthValue.evaluate(null));
		assertFalse(ObjectTruthValue.evaluate(0));
		assertTrue(ObjectTruthValue.evaluate(1));
		assertTrue(ObjectTruthValue.evaluate(-1));
		assertTrue(ObjectTruthValue.evaluate(Integer.MAX_VALUE));
		assertTrue(ObjectTruthValue.evaluate(Integer.MIN_VALUE));
	}
	
	public void testObject() throws Exception {
		assertFalse(ObjectTruthValue.evaluate(null));
		assertTrue(ObjectTruthValue.evaluate(new Object()));
	}
	
}
