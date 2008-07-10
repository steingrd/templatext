package org.templatext.template;

import java.io.PrintWriter;

import org.templatext.template.core.NodeList;

/**
 * A Template object represents a compiled template. Templates are compiled from
 * template strings into a sequence of nodes. A template can be rendered using a
 * context.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class Template {

	private NodeList template;

	public Template(NodeList template) {
		this.template = template;
	}

	/**
	 * Renders the template with the given context. Variables in this template
	 * are resolved using the context and replaced with the String value of the
	 * context variable.
	 * 
	 * @param context the context used to render the template
	 * @return the templated rendered with the given context
	 * @throws TemplateException
	 */
	public String render(Context context) {
		return render(context, null);
	}

	/**
	 * Renders the template with the the given context and writes the result to
	 * the given PrintWriter `writer`.
	 * 
	 */
	public String render(Context context, PrintWriter writer) {
		String result = template.render(context);

		if (writer != null) {
			writer.write(result);
		}

		return result;
	}
	
	
	public NodeList nodes() {
		return this.template;
	}
}
