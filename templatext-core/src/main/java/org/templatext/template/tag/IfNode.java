package org.templatext.template.tag;

import org.templatext.template.Context;
import org.templatext.template.TemplateNode;
import org.templatext.template.Variable;
import org.templatext.template.core.NodeList;
import org.templatext.template.utils.ObjectTruthValue;

/**
 * If statements take a single variable name that represents the test. The
 * variable should map to a Boolean object, if the object is true, the trueBlock
 * is rendered, if the object is false the elseBlock is rendered.
 * <p>
 * The else-block is optional.
 * <p>
 * Syntax:
 * 
 * <pre>
 * % if variable %} ... {% else %} ... {% endif %}
 * </pre>
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
