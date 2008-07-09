package org.javango.template.filter;

import org.javango.template.TemplateFilter;

public abstract class AbstractStringFilter implements TemplateFilter {

	protected abstract String filterString(String string);
	
	public Object filter(Object object, String arg) {
		if (!object.getClass().equals(String.class)) {
			return "";
		}
		
		return filterString((String)object);
	}

}
