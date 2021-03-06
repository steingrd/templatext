package org.templatext.template.core;

import java.util.ArrayList;
import java.util.List;

import org.templatext.template.TemplateFilter;

public class FilterChain {
	
	private List<FilterInvocation> chain;

	public FilterChain() {
		this.chain = new ArrayList<FilterInvocation>();
	}
	
	public FilterChain(List<FilterInvocation> chain) {
		this.chain = chain;
	}

	public Object apply(Object object) {
		for (FilterInvocation filter : chain) {
			object = filter.invoke(object);
		}
		return object;
	}
	
	public int size() {
		return chain.size();
	}

	public void add(TemplateFilter filter, String argument) {
		chain.add(new FilterInvocation(filter, argument));
	}
	
}
