package org.templatext.template;

import java.util.HashMap;
import java.util.Map;

/**
 * A context is a key-value mapping of objects. When rendering a template,
 * variables are resolved in the current context, which is a object of this
 * class.
 * 
 * The context also keeps track of the current template and the configuration,
 * use the <code>template()</code> and <code>configuration()</code> methods to
 * access them in custom template tags.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class Context {

	private Map<String, Object> context;

	/**
	 * Creates an empty context.
	 */
	public Context() {
		this(new HashMap<String, Object>());
	}

	/**
	 * Creates a context using the values in the given map.
	 */
	public Context(Map<String, Object> context) {
		this.context = context;
	}

	/**
	 * Returns true if <code>name</code> is contained in this context, false
	 * otherwise.
	 * 
	 * @param name
	 *            a name to search the context for
	 * @return true if the given name is in the context
	 */
	public boolean contains(String name) {
		return context.containsKey(name);
	}

	/**
	 * Returns the object that is associated with the given <code>name</code> in
	 * this context. If no object is associated with the given name, a null
	 * reference is returned.
	 * 
	 * @param name
	 *            a name to search the context for
	 * @return object associated with name or null reference
	 */
	public Object get(String name) {
		return context.get(name);
	}

	/**
	 * Associates an object with a name in the context. If the name already
	 * appears in the context, the value it refers to is overwritten with
	 * <code>object</code>.
	 * 
	 * @param name
	 *            the name to associate object with
	 * @param object
	 *            the object to associate with name
	 */
	public void put(String name, Object object) {
		context.put(name, object);
	}

	/**
	 * Removes the object associated with the given name in this context. If no
	 * object is associated with <code>name</code> this method does nothing.
	 * 
	 * @param name
	 */
	public void remove(String name) {
		context.remove(name);
	}

	/**
	 * Returns the {@link Configuration} that is used in this context.
	 * 
	 * @return a {@link Configuration} for this context
	 */
	public Configuration configuration() {
		return (Configuration) context.get("__configuration");
	}

	/**
	 * Returns the {@link Template} that this context renders.
	 * 
	 * @return a {@link Template} for this context
	 */
	public Template template() {
		return (Template) context.get("__template");
	}

	/**
	 * Creates a {@link Context} for the given template with the given model and
	 * configuration.
	 * 
	 * The model and the configuration objects are added to the context using
	 * the special, internal context keys.
	 */
	public static Context forTemplate(Template template, Configuration configuration, Map<String, Object> model) {
		Context context = new Context(model);
		context.put("__template", template);
		context.put("__configuration", configuration);
		return context;
	}
}
