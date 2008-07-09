package org.javango.template.tag;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.TemplateTagParser;
import org.javango.template.compiler.Token;
import org.javango.template.core.NodeList;

public class IfParser implements TemplateTagParser {

	public TemplateNode parse(Parser parser, Token token) {
		String[] splittedContents = token.splitContents();
		
		if (splittedContents.length != 2) {
			throw new TemplateException("if tag expects two elements, got " + splittedContents.length);
		}
		
		String testVariableName = splittedContents[1];
		NodeList block = parser.parse(new String[] { "else", "endif" });
		
		Token next = parser.getTokenStream().next();
		if (next.splitContents()[0].equals("endif")) {
			return new IfNode(testVariableName, block, null);
		}
		
		assert next.splitContents()[0].equals("else");
		
		NodeList trueBlock = block;
		NodeList elseBlock = parser.parse("endif");
		
		next = parser.getTokenStream().next();
		assert next.splitContents()[0].equals("endif");
		
		return new IfNode(testVariableName, trueBlock, elseBlock);
	}

}
