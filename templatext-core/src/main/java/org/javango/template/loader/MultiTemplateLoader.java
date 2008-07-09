package org.javango.template.loader;

import java.io.IOException;
import java.util.List;

import org.javango.template.Template;
import org.javango.template.TemplateException;
import org.javango.template.TemplateLoader;

// FIXME implement this class
public class MultiTemplateLoader implements TemplateLoader {

	public MultiTemplateLoader(List<TemplateLoader> templateLoaders) {
	}

	public Template load(String name) throws TemplateException, IOException {
		return null;
	}

}
