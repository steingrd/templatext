package org.javango.spring.view;

import java.io.File;
import java.io.IOException;

import org.javango.template.Template;
import org.javango.template.TemplateException;
import org.javango.template.TemplateLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Javango template loader that loads via a Spring {@link ResourceLoader}. Used
 * for any paths that cannot be resolved as a {@link File}.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class SpringTemplateLoader implements TemplateLoader {

	private final ResourceLoader resourceLoader;
	
	private final String templateLoaderPath;
	
	public SpringTemplateLoader(ResourceLoader resourceLoader, String templateLoaderPath) {
		this.resourceLoader = resourceLoader;
		if (!templateLoaderPath.endsWith("/")) {
			templateLoaderPath += "/";
		}
		this.templateLoaderPath = templateLoaderPath;
	}
	
	public Template load(String name) throws TemplateException, IOException {
		Resource resource = this.resourceLoader.getResource(this.templateLoaderPath + name);
		return resource.exists() ? new Template(resource.getInputStream()) : null;
	}

}
