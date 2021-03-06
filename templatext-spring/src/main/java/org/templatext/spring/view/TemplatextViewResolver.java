package org.templatext.spring.view;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class TemplatextViewResolver extends AbstractTemplateViewResolver {

	public TemplatextViewResolver() {
		setViewClass(requiredViewClass());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Class requiredViewClass() {
		return TemplatextView.class;
	}
	
}
