package org.templatext.template.filter;

public class LowerFilter extends AbstractStringFilter {

	@Override
	protected String filterString(String string) {
		return string.toLowerCase();
	}

}
