package org.javango.template.tag;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.TemplateTagParser;
import org.javango.template.compiler.Token;

public abstract class AbstractSingleStringArgumentParser<T extends TemplateNode> implements TemplateTagParser {

	public TemplateNode parse(Parser parser, Token token) {
		String[] contents = token.splitContents();
		if (contents.length != 2) {
			throw new TemplateException("Missing parent template name argument");
		}
		
		return createNode(contents[1]);
	}
	
	protected abstract T createNode(String argument);
}
