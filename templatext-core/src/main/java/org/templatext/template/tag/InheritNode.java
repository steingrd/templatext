package org.templatext.template.tag;

import java.io.IOException;

import org.templatext.template.Context;
import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateLoader;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateNotFoundException;

/**
 * The <code>inherit</code> tag is used to declare that a template inherits the
 * contents of another template. The child template can use the `override` tag
 * to override named blocks from its parent.
 * <p>
 * Syntax:
 * 
 * <pre>
 * % inherit filename.html %}
 * </pre>
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class InheritNode implements TemplateNode {

	private String parentTemplateName;

	public InheritNode(String parent) {
		this.parentTemplateName = parent;
	}

	public String render(Context context) {
		TemplateLoader loader = context.configuration().getTemplateLoader();
		Template parent;

		try {
			parent = loader.load(parentTemplateName);
		} catch (TemplateNotFoundException e) {
			throw new TemplateException("could not load parent template: " + parentTemplateName, e);
		} catch (IOException e) {
			throw new TemplateException("could not load parent template: " + parentTemplateName, e);
		}

		createOverrides(context);

		return parent.render(context);
	}

	private void createOverrides(Context context) {
		Template child = context.template();
		for (TemplateNode node : child.nodes()) {
			if (node.getClass().equals(OverrideNode.class)) {
				OverrideNode override = (OverrideNode) node;
				context.put("__override_" + override.name(), override);
			}
		}
	}

}
