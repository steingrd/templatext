package org.templatext.template.tag;

import org.templatext.template.Parser;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateTagParser;
import org.templatext.template.compiler.Token;
import org.templatext.template.core.NodeList;

public class ForParser implements TemplateTagParser {

	public TemplateNode parse(Parser parser, Token token) {
		String[] splittedContents = token.splitContents();
		
		if (splittedContents.length != 4) {
			throw new TemplateException("for tag expects four elements, got " + splittedContents.length);
		}
		
		String loopVariableName = splittedContents[1];
		String sequenceVariableName = splittedContents[3];
		NodeList body = parser.parse("endfor");
		
		Token next = parser.getTokenStream().next();
		assert next.splitContents()[0].equals("endfor");
		
		return new ForNode(loopVariableName, sequenceVariableName, body);
	}

}
