package org.templatext.template.tag;

import java.io.IOException;

import org.templatext.template.Configuration;
import org.templatext.template.Context;
import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateNotFoundException;

/**
 * The <code>insert</code> tag is used to insert a named template using the
 * current context. The tag takes a single argument, a template name, and
 * renders the template using the current context.
 * <p>
 * The TemplateLoader found in the current Configuration through the Context is
 * used to load the named template.
 * <p>
 * Syntax:
 * 
 * <pre>
 *   {% insert filename.html %}
 * </pre>
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class InsertNode implements TemplateNode {

	private String templateName;

	public InsertNode(String templateName) {
		this.templateName = templateName;
	}

	public String render(Context context) {
		if (templateName == null || templateName.equals("")) {
			throw new TemplateException("invalid template name");
		}

		Configuration configuration = context.configuration();
		TemplateLoader loader = configuration.getTemplateLoader();
		Template template;
		try {
			template = loader.load(templateName);
		} catch (TemplateNotFoundException e) {
			throw new TemplateException("failed to load template: " + templateName, e);
		} catch (IOException e) {
			throw new TemplateException("failed to load template: " + templateName, e);
		}

		return template.render(context);
	}

}
