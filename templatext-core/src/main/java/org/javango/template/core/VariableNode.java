package org.javango.template.core;

import org.javango.template.Context;
import org.javango.template.TemplateNode;

/**
 * Variable nodes substitute the text of a variable in the given context with
 * the string value of the variable. A variable can be a simple variable or a
 * variable with a dot-accessor. The variable can be passed through multiple
 * filters.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class VariableNode implements TemplateNode {

	private FilterExpression filterExpression;

	public VariableNode(FilterExpression filterExpression) {
		this.filterExpression = filterExpression;
	}

	public String render(Context context) {
		Object obj = filterExpression.evaluate(context);
		return obj == null ? "" : obj.toString();
	}

	@Override
	public String toString() {
		return "<VariableNode: " + filterExpression + ">";
	}
}
