package org.templatext.template.filter;

import org.templatext.template.TemplateFilter;
import org.templatext.template.TemplateSyntaxErrorException;
import org.templatext.template.utils.ObjectTruthValue;

public class DefaultFilter implements TemplateFilter {

	public Object filter(Object object, String arg) {
		if (arg == null) {
			throw new TemplateSyntaxErrorException("missing argument");
		}
		
		return ObjectTruthValue.evaluate(object) ? object : arg;
	}

}
