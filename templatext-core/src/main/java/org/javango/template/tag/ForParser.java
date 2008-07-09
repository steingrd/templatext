package org.javango.template.tag;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.TemplateTagParser;
import org.javango.template.compiler.Token;
import org.javango.template.core.NodeList;

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
