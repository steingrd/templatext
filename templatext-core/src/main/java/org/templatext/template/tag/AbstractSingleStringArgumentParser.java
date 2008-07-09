package org.templatext.template.tag;

import org.templatext.template.Parser;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateTagParser;
import org.templatext.template.compiler.Token;

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
