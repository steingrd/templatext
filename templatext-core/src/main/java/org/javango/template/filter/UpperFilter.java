package org.javango.template.filter;

public class UpperFilter extends AbstractStringFilter {

	@Override
	protected String filterString(String string) {
		return string.toUpperCase();
	}

}
