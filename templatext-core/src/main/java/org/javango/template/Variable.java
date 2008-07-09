package org.javango.template;

import java.util.ArrayList;
import java.util.List;

import org.javango.template.core.VariableChain;

public class Variable {

	private String name;
	private VariableChain chain;
	
	public Variable(String variable) {
		split(variable);
	}

	private void split(String variable) {
		if (!variable.contains(".")) {
			name = variable;
			chain = null;
			return;
		}
		
		String[] parts = variable.split("\\.");
		name = parts[0];
		chain = new VariableChain(collect(parts));
	}

	private List<String> collect(String[] parts) {
		List<String> chain = new ArrayList<String>();
		for (int i = 1; i < parts.length; i++) {
			chain.add(parts[i]);
		}
		return chain;
	}

	public String getName() {
		return name;
	}

	public Object resolve(Context context) {
		if (!context.contains(name)) {
			return "";
		}
		
		Object object = context.get(name);
		return chain == null ? object : chain.resolve(object); 
	}
	
	@Override
	public String toString() {
		return "<Variable: " + name + ">";
	}
}
