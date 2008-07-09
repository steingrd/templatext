package org.templatext.template.tag;

import org.templatext.template.Parser;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateTagParser;
import org.templatext.template.compiler.Token;

public class CycleParser implements TemplateTagParser {

	public TemplateNode parse(Parser parser, Token token) {
		String[] contents = token.splitContents();
		String[] elements = new String[contents.length - 1];
		System.arraycopy(contents, 1, elements, 0, elements.length);
		return new CycleNode(elements);
	}

}
