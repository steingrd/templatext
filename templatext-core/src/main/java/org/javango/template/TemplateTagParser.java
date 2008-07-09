package org.javango.template;

import org.javango.template.compiler.Token;

/**
 * Interface for core and custom template tags. Implement this interface in your
 * custom template tag.
 * 
 * The {@link #parse(Parser, Token)} method should return a {@link TemplateNode}
 * object that renders the tag. Use the {@link Token} object to retrieve the
 * contents of the tag and the {@link Parser} object if subsequent parsing is
 * required.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public interface TemplateTagParser {

	/**
	 * This method should return a {@link TemplateNode} that renders the custom
	 * tag.
	 * 
	 * Use the {@link Parser} parameter if more parsing is required, for example
	 * until a special end-tag. The {@link Token} parameter holds the contents
	 * of the tag, i.e. arguments and so on.
	 * 
	 * @param parser a {@link Parser} for the current token stream
	 * @param token a {@link Token} object for this specific tag
	 * @return a {@link TemplateNode} that renders this tag
	 * @throws TemplateException if an error occurs during compilation
	 */
	abstract TemplateNode parse(Parser parser, Token token);

}
