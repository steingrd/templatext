package org.templatext.template.loader;

import java.io.IOException;
import java.util.List;

import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;

// FIXME implement this class
public class MultiTemplateLoader implements TemplateLoader {

	public MultiTemplateLoader(List<TemplateLoader> templateLoaders) {
	}

	public Template load(String name) throws TemplateException, IOException {
		return null;
	}

}
