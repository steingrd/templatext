package org.templatext.template.filter;

import org.templatext.template.TemplateFilter;

public abstract class AbstractStringWithArgumentFilter implements TemplateFilter {

	protected abstract String filterString(String string, String arg);
	
	public Object filter(Object object, String arg) {
		if (!object.getClass().equals(String.class)) {
			return "";
		}
		
		return filterString((String)object, arg);
	}
	
}
