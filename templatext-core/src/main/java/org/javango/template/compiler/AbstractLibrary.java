package org.javango.template.compiler;

import java.util.HashMap;
import java.util.Map;

import org.javango.template.TemplateException;

public abstract class AbstractLibrary<T> {

	private Map<String, T> library = new HashMap<String, T>();

	public AbstractLibrary() {
		initialize();
	}
	
	protected void initialize() {
	}
	
	public T resolve(String name) {
		if (!library.containsKey(name)) {
			throw new TemplateException("could not find component for name: " + name);
		}
		
		return library.get(name);
	}

	public void register(String name, T obj) {
		library.put(name, obj);
	}
}
