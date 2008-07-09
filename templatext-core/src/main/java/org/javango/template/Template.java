package org.javango.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.javango.template.compiler.FilterLibrary;
import org.javango.template.compiler.ParserImpl;
import org.javango.template.compiler.TagLibrary;
import org.javango.template.core.NodeList;

/**
 * A Template object represents a compiled template. Templates are compiled from
 * template strings into a sequence of nodes. A template can be rendered using a
 * context.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class Template {

	private NodeList template;

	public Template(String templateString) {
		this.template = new ParserImpl(templateString, new TagLibrary(), new FilterLibrary()).parse();
	}

	public Template(NodeList template) {
		this.template = template;
	}

	// TODO refactor this and the part in FileSystemTemplateLoader to a better place
	public Template(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer templateString = new StringBuffer();
		try {
			String line = reader.readLine();
			while (line != null && !"".equals(line)) {
				templateString.append(line);
				// readLine strips away the newline, we want to keep it to
				// preserve the format the template author used
				templateString.append("\n");
				line = reader.readLine();
			}
		} catch (IOException e) {
			throw new TemplateException(e.getMessage());
		}
		this.template = new ParserImpl(templateString.toString(), new TagLibrary(), new FilterLibrary()).parse();
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
