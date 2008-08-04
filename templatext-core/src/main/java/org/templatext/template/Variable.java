package org.templatext.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.templatext.template.core.VariableChain;

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
		
		List<String> chainList = Arrays.asList(parts);
		chainList = chainList.subList(1, chainList.size());
		
		name = parts[0];
		chain = new VariableChain(chainList);
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
