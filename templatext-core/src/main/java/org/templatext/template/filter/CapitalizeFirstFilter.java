package org.templatext.template.filter;

public class CapitalizeFirstFilter extends AbstractStringFilter {

	@Override
	protected String filterString(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

}
