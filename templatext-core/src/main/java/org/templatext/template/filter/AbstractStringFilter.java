package org.templatext.template.filter;

import org.templatext.template.TemplateFilter;

public abstract class AbstractStringFilter implements TemplateFilter {

	protected abstract String filterString(String string);
	
	public Object filter(Object object, String arg) {
		if (!object.getClass().equals(String.class)) {
			return "";
		}
		
		return filterString((String)object);
	}

}
