package org.javango.template.tag;

import org.javango.template.Parser;
import org.javango.template.TemplateNode;
import org.javango.template.TemplateTagParser;
import org.javango.template.compiler.Token;

public class CycleParser implements TemplateTagParser {

	public TemplateNode parse(Parser parser, Token token) {
		String[] contents = token.splitContents();
		String[] elements = new String[contents.length - 1];
		System.arraycopy(contents, 1, elements, 0, elements.length);
		return new CycleNode(elements);
	}

}
