package org.javango.template;

import org.javango.template.loader.FileSystemTemplateLoader;

public class Configuration {

	private TemplateLoader templateLoader = new FileSystemTemplateLoader();

	public TemplateLoader getTemplateLoader() {
		return templateLoader;
	}

	public void setTemplateLoader(TemplateLoader templateLoader) {
		this.templateLoader = templateLoader;
	}
	
}
