package org.javango.template.filter;

import java.lang.reflect.Array;
import java.util.Collection;

import org.javango.template.TemplateFilter;

public class LengthFilter implements TemplateFilter {

	@SuppressWarnings("unchecked")
	public Object filter(Object object, String arg) {
		if (object.getClass().isArray()) {
			return Array.getLength(object);
		} 
		
		if (Collection.class.isAssignableFrom(object.getClass())) {
			return ((Collection)object).size();
		} 
		
		if (String.class.isAssignableFrom(object.getClass())) {
			return ((String)object).length();
		}
		
		return 0; // TODO find out what django does 
	}

}
