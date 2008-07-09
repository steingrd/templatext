package org.templatext.spring.view;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;

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
