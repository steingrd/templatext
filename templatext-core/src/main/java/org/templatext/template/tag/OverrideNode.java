package org.templatext.template.tag;

import org.templatext.template.Context;
import org.templatext.template.TemplateNode;
import org.templatext.template.core.NodeList;

/**
 * Template inheritance is a key feature in the template language. This class
 * represents the template node for a block that overrides a template block in
 * the parent template. 
 * 
 * In a template:
 *   
 *   {% block sidebar %}
 *     ...
 *   {% endblock %}
 *   
 * The same syntax is used in the child template to override this part. The effect
 * is that the child block renders instead of the parent block.
 * 
 * @see OverrideParser
 * @see InheritNode
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class OverrideNode implements TemplateNode {

	private String name;
	private NodeList block;
	
	public OverrideNode(String name, NodeList block) {
		this.name = name;
		this.block = block;
	}

	public String render(Context context) {
		String override_key = "__override_" + name;
		if (context.contains(override_key)) {
			TemplateNode node = (TemplateNode)context.get(override_key);
			context.remove(override_key);
			return node.render(context);
		}
		
		return block.render(context);
	}

	public String name() {
		return name;
	}

}
