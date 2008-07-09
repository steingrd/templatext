package org.javango.template.tag;

import org.javango.template.Context;
import org.javango.template.TemplateNode;
import org.javango.template.Variable;
import org.javango.template.core.NodeList;
import org.javango.template.utils.ObjectTruthValue;

/**
 * If statements take a single variable name that represents the test. The
 * variable should map to a Boolean object, if the object is true, the trueBlock
 * is rendered, if the object is false the elseBlock is rendered.
 * 
 * The else-block is optional.
 * 
 * Syntax:
 *   {% if variable %} ... {% else %} ... {% endif %}
 * 
 * TODO other test variables than booleans
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class IfNode implements TemplateNode {

	private Variable test;
	private NodeList trueBlock;
	private NodeList elseBlock;
	
	public IfNode(String test, NodeList trueBlock, NodeList elseBlock) {
		this.test = new Variable(test);
		this.trueBlock = trueBlock;
		this.elseBlock = elseBlock;
	}
	
	public String render(Context context) {
		Object object = test.resolve(context);

		if (elseBlock == null) {
			return ObjectTruthValue.evaluate(object) ? trueBlock.render(context) : "";
		} else {
			return ObjectTruthValue.evaluate(object) ? trueBlock.render(context) : elseBlock.render(context);
		}
	}
	
	@Override
	public String toString() {
		return "<IfNode: " + test + ">";
	}

}
