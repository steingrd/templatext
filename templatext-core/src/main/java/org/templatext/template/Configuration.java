package org.templatext.template;

import org.templatext.template.loader.FileSystemTemplateLoader;

/**
 * The main configuration class for Templatext templates. Templates are rendered
 * using a configuration object. The configuration objects is accessible for
 * template tags through the current context.
 * <p>
 * To configure the template system override the properties of this class.
 * <p>
 * The default is to use a {@link FileSystemTemplateLoader}.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class Configuration {

	private TemplateLoader templateLoader = new FileSystemTemplateLoader();

	/**
	 * Returns the {@link TemplateLoader} for this configuration.
	 */
	public TemplateLoader getTemplateLoader() {
		return templateLoader;
	}

	/**
	 * Sets the {@link TemplateLoader} for this configuration.
	 * <p>
	 * If not set the default template loader is used.
	 */
	public void setTemplateLoader(TemplateLoader templateLoader) {
		this.templateLoader = templateLoader;
	}

}
