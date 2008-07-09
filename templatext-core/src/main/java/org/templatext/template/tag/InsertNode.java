package org.templatext.template.tag;

import org.templatext.template.Configuration;
import org.templatext.template.Context;
import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;
import org.templatext.template.TemplateNode;

/**
 * The `insert` tag is used to insert a named template using the current
 * context. The tag takes a single argument, a template name, and renders the
 * template using the current context.
 * 
 * Syntax:
 * 
 *   {% insert filename.html %}
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
		} catch (Exception e) {
			throw new TemplateException("failed to load template: " + e.getMessage());
		}
		return template.render(context);
	}

}
