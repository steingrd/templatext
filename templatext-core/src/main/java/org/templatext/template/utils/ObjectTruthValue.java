package org.templatext.template.utils;

public class ObjectTruthValue {
	
	public static boolean evaluate(Object object) {
		if (object == null) { 
			return false;
		}
		
		if (Boolean.class.isAssignableFrom(object.getClass())) {
			Boolean b = (Boolean)object;
			return b.booleanValue();
		}
		
		if (Integer.class.isAssignableFrom(object.getClass())) {
			Integer i = (Integer)object;
			return i != 0;
		}
		
		if (String.class.isAssignableFrom(object.getClass())) {
			String s = (String)object;
			return !s.equals("");
		}
		
		return true;
	}
	
}
