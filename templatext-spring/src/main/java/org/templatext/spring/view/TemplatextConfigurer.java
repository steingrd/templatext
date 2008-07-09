package org.templatext.spring.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.ServletContextAware;
import org.templatext.template.Configuration;
import org.templatext.template.TemplateLoader;
import org.templatext.template.loader.FileSystemTemplateLoader;
import org.templatext.template.loader.MultiTemplateLoader;

// FIXME add support for custom template loaders

/**
 * Java bean to configure Javango templates for web mvc use. The simplest way to
 * use this class is to set the templateLoaderPath property. It is also possible
 * to set the configuration property to a {@link Configuration} java bean.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class TemplatextConfigurer implements TemplatextConfig, InitializingBean, ServletContextAware, ResourceLoaderAware {

	private Configuration configuration;

	private String[] templateLoaderPaths;

	private ResourceLoader resourceLoader;

	private List<TemplateLoader> templateLoaders = new ArrayList<TemplateLoader>();

	private boolean fileSystemAccessPreferred = true;

	/**
	 * Initializes the configuration bean if it has not bean overridden by a
	 * preconfigured Configuration bean.
	 */
	public void afterPropertiesSet() throws Exception {
		if (this.resourceLoader == null) {
			this.resourceLoader = new DefaultResourceLoader();
		}
		if (this.configuration == null) {
			this.configuration = createConfiguration();
		}
	}

	public void setServletContext(ServletContext servletContext) {
		// for future use without breaking api 
	}

	/**
	 * Sets the Spring {@link ResourceLoader} to use for loading Javango
	 * template files. The default is {@link DefaultResourceLoader}. Is
	 * overridden by the ApplicationContext if running in a context.
	 */
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * Sets the template loader path.
	 * 
	 * @param templateLoaderPath
	 */
	public void setTemplateLoaderPath(String templateLoaderPath) {
		this.templateLoaderPaths = new String[] { templateLoaderPath };
	}

	/**
	 * Returns wether to prefer file system access for template loading or not.
	 * 
	 * @return
	 */
	public boolean isFileSystemAccessPreferred() {
		return fileSystemAccessPreferred;
	}

	/**
	 * Set wether to prefer file system access or not for template loading. File
	 * system access enables detection of changes in templates for reloading.
	 * <p>
	 * Default is true
	 * <p>
	 * Turn this off to always load via the SpringTemplateLoader which might be
	 * necessary if some of your templates reside in expanded classes directory,
	 * while others live in jar files.
	 * 
	 * @param fileSystemAccessPreferred
	 */
	public void setFileSystemAccessPreferred(boolean fileSystemAccessPreferred) {
		this.fileSystemAccessPreferred = fileSystemAccessPreferred;
	}

	/**
	 * Returns the Javango {@link Configuration} bean wrapped by this bean.
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Set a preconfigured Javango {@link Configuration} bean. If this is not
	 * set, either the properties of this class has to be set or the default
	 * values are used.
	 * 
	 * @param configuration
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Prepare a {@link Configuration} object and return it. Uses the properties
	 * of this class to prepare the bean. Properties used are
	 * templateLoaderPath, resourceLoader and servletContext.
	 * 
	 * @return a configured Configuration bean.
	 */
	private Configuration createConfiguration() {
		Configuration configuration = newConfiguration();

		if (this.templateLoaderPaths != null) {
			for (String path : templateLoaderPaths) {
				this.templateLoaders.add(createTemplateLoaderForPath(path));
			}
		}

		configuration.setTemplateLoader(createAggregateTemplateLoader());
		
		postProcessConfiguration(configuration);
		return configuration;
	}

	private TemplateLoader createAggregateTemplateLoader() {
		int count = this.templateLoaders.size();
		switch (count) {
		case 0:
			return null;
		case 1:
			return this.templateLoaders.get(0);
		default:
			return new MultiTemplateLoader(this.templateLoaders);
		}
	}

	/**
	 * Create a Javango {@link TemplateLoader} for the given path. Default
	 * implementation returns a {@link FileSystemTemplateLoader}.
	 * 
	 * @param path
	 * @return
	 */
	protected TemplateLoader createTemplateLoaderForPath(String templateLoaderPath) {
		if (!isFileSystemAccessPreferred()) {
			return new SpringTemplateLoader(this.resourceLoader, templateLoaderPath);
		}
		
		try {
			Resource path = this.resourceLoader.getResource(templateLoaderPath);
			File directory = path.getFile(); // fails if not resolvable
			return new FileSystemTemplateLoader(directory);
		} catch (IOException e) {
			return new SpringTemplateLoader(this.resourceLoader, templateLoaderPath);
		}
	}

	/**
	 * Returns a {@link Configuration} object. Subclasses can override this to
	 * return a preconfigured Configuration object with custom settings, or for
	 * using a mock object for testing. .
	 * 
	 * @return a {@link Configuration} object
	 */
	protected Configuration newConfiguration() {
		return new Configuration();
	}

	/**
	 * Can be overriden by subclasses to perform additional configuration of a
	 * {@link Configuration} object after it has been created and configured.
	 * 
	 * @param configuration
	 */
	protected void postProcessConfiguration(Configuration configuration) {
	}
}
