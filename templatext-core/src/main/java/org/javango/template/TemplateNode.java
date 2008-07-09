package org.javango.template;


/**
 * A template node is the result of compiling a template token and it is an
 * object that is responsible for creating some sort of output when given a
 * context.
 * 
 * Template tag authors can implement this interface to define custom template
 * nodes. Use a {@link TemplateTagParser} to parse a custom template tag.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public interface TemplateNode {

	String render(Context context);

}
