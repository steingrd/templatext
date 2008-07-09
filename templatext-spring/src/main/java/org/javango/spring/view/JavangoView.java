package org.javango.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javango.template.Configuration;
import org.javango.template.Context;
import org.javango.template.Template;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.view.AbstractTemplateView;

public class JavangoView extends AbstractTemplateView {

	private Configuration configuration;

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedTemplateModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		doTemplateRender(model, request, response);
	}

	/**
	 * Render the JavangoView view to the given response, using the given model.
	 * <p>
	 * The default implementation renders the template specified by the "url"
	 * bean property, retrieved via <code>loadTemplate</code>. It delegates to
	 * the <code>processTemplate</code> method to merge the template instance
	 * with the given template model.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	protected void doTemplateRender(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		processTemplate(loadTemplate(getUrl()), model, response);
	}

	/**
	 * Process the Javango {@link Template} instance to the servlet response.
	 * Can be overriden to add custom behavior.
	 * 
	 * @param loadTemplate
	 * @param model
	 * @param response
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	protected void processTemplate(Template template, Map model, HttpServletResponse response) throws Exception {
		Context context = Context.forTemplate(template, this.configuration, model);
		template.render(context, response.getWriter());
	}

	/**
	 * Returns the {@link Template} specified by the given name using the
	 * template loader in the current {@link Configuration}.
	 * 
	 * @param name
	 * @return
	 */
	protected Template loadTemplate(String name) throws Exception {
		return this.configuration.getTemplateLoader().load(name);
	}

	/**
	 * Invoked on startup. Either uses a injected {@link JavangoConfig} or
	 * searches the application context for a {@link JavangoConfig} bean and
	 * uses it.
	 */
	@Override
	protected void initApplicationContext() throws BeansException {
		super.initApplicationContext();

		if (this.configuration == null) {
			this.configuration = findContextConfiguration().getConfiguration();
		}
	}

	/**
	 * Locates a single {@link JavangoConfig} bean in the application context.
	 * 
	 * @return
	 */
	protected JavangoConfig findContextConfiguration() {
		try {
			return (JavangoConfig) BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(), JavangoConfig.class, true, false);
		} catch (NoSuchBeanDefinitionException ex) {
			throw new ApplicationContextException(
					"Must define a single JavangoConfig bean (may be inherited). JavangoConfigurer is the default implementation.");
		}
	}

	/**
	 * Set the Javango {@link Configuration} to be used by this view. If this is
	 * not set the default lookup is performed: a single {@link JavangoConfig}
	 * is expected in the web application context, with any name.
	 * 
	 * @param configuration
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
