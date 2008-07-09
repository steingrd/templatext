package org.javango.template.filter;

import org.javango.template.TemplateFilter;
import org.javango.template.TemplateSyntaxErrorException;
import org.javango.template.utils.ObjectTruthValue;

public class DefaultFilter implements TemplateFilter {

	public Object filter(Object object, String arg) {
		if (arg == null) {
			throw new TemplateSyntaxErrorException("missing argument");
		}
		
		return ObjectTruthValue.evaluate(object) ? object : arg;
	}

}
