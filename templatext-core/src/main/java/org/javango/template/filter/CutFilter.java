package org.javango.template.filter;

public class CutFilter extends AbstractStringWithArgumentFilter {

	@Override
	protected String filterString(String string, String arg) {
		return string.replaceAll(arg, "");
	}

}
