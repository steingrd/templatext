package org.templatext.template.loader;

import java.io.IOException;
import java.util.List;

import org.templatext.template.Template;
import org.templatext.template.TemplateLoader;
import org.templatext.template.TemplateNotFoundException;

/**
 * Template loader that loads from multiple template loaders. The load method
 * tries every loader in the template loader list until it finds a loader that
 * does not throw a TemplateNotFoundException.
 * 
 * If every loader throws a TemplateNotFoundException this loader does the same.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class MultiTemplateLoader implements TemplateLoader {

	private List<TemplateLoader> loaders;

	public MultiTemplateLoader(List<TemplateLoader> loaders) {
		this.loaders = loaders;
	}

	public Template load(String name) throws TemplateNotFoundException, IOException {
		for (TemplateLoader loader : loaders) {
			try {
				return loader.load(name);
			} catch (TemplateNotFoundException e) {
			}
		}

		throw new TemplateNotFoundException("Could not load template with name: " + name);
	}

}
