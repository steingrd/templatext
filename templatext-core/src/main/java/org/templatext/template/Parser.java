package org.templatext.template;

import org.templatext.template.compiler.TokenStream;
import org.templatext.template.core.NodeList;

public interface Parser {

	NodeList parse(String until);
	
	NodeList parse(String[] until);
	
	TokenStream getTokenStream();
	
}
