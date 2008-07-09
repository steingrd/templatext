package org.templatext.template.tag;

import org.templatext.template.Context;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.Variable;

/**
 * Cycle among the given strings each time this tag is encountered. The tag
 * should appear within a loop.
 * 
 * You can use any number of values, separated by spaces. Values enclosed in
 * single (‘) or double quotes (“) are treated as string literals, while values
 * without quotes are assumed to refer to context variables.
 * 
 * {% for o in some_list %}
 *   <tr class="{% cycle 'row1' 'row2' %}"> ... </tr> 
 * {% endfor %}
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class CycleNode implements TemplateNode {

	private String[] elements;

	public CycleNode(String[] elements) {
		this.elements = elements;
	}

	public String render(Context context) {
		// FIXME resolve() returns "" if it fails, should it throw an exception?
		Object obj = new Variable("forloop.counter0").resolve(context);
		if (!Integer.class.equals(obj.getClass())) {
			throw new TemplateException("cycle tag not inside a for loop: could not resolve forloop.counter0 as integer");
		}
		
		Integer count = (Integer)obj;
		return elements[count.intValue() % elements.length];
	}

}
