package org.javango.template;

import org.javango.template.compiler.TokenStream;
import org.javango.template.core.NodeList;

public interface Parser {

	NodeList parse(String until);
	
	NodeList parse(String[] until);
	
	TokenStream getTokenStream();
	
}
