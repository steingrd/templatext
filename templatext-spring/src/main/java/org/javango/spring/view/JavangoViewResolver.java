package org.javango.spring.view;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class JavangoViewResolver extends AbstractTemplateViewResolver {

	public JavangoViewResolver() {
		setViewClass(requiredViewClass());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Class requiredViewClass() {
		return JavangoView.class;
	}
	
}
