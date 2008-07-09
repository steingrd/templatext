package org.javango.template.tag;

import java.io.IOException;

import org.javango.template.Context;
import org.javango.template.Template;
import org.javango.template.TemplateException;
import org.javango.template.TemplateLoader;
import org.javango.template.TemplateNode;

/**
 * The `inherit` tag is used to declare that a template inherits the contents of
 * another template. The child template can use the `override` tag to override
 * named blocks from its parent.
 * 
 * Syntax: 
 * 
 *   {% inherit filename.html %}
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
		} catch (TemplateException e) {
			return ""; // FIXME
		} catch (IOException e) {
			return ""; 
		}
		
		createOverrides(context);
		
		return parent.render(context);
	}

	private void createOverrides(Context context) {
		Template child = context.template();
		for (TemplateNode node : child.nodes()) {
			if (node.getClass().equals(OverrideNode.class)) {
				OverrideNode override = (OverrideNode)node;
				context.put("__override_" + override.name(), override);
			}
		}
	}

}
