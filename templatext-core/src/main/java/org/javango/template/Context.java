package org.javango.template;

import java.util.HashMap;
import java.util.Map;

public class Context {
	
	private Map<String, Object> context;
	
	public Context() {
		this(new HashMap<String, Object>(), new Configuration());
	}
	
	public Context(Map<String, Object> context, Configuration configuration) {
		this.context = context;
		this.context.put("__configuration", configuration);
	}

	public boolean contains(String name) {
		return context.containsKey(name);
	}
	
	public Object get(String name) {
		return context.get(name);
	}

	public void put(String name, Object object) {
		context.put(name, object);
	}
	
	public void remove(String name) {
		context.remove(name);
	}
	
	public Configuration configuration() {
		return (Configuration) context.get("__configuration");
	}

	public Template template() {
		return (Template) context.get("__template");
	}
	
	public static Context forTemplate(Template template, Configuration configuration, Map<String,Object> model) {
		Context context = new Context(model, configuration);
		context.put("__template", template);
		return context;
	}
}
