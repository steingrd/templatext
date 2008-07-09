package org.javango.template.compiler;

import org.javango.template.TemplateFilter;

public class FilterInvocation {
	
	private TemplateFilter filter;
	private String argument;
	
	public FilterInvocation(TemplateFilter filter, String argument) {
		this.filter = filter;
		this.argument = argument;
	}

	public Object invoke(Object object) {
		return filter.filter(object, argument);
	}
	
}
