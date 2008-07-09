package org.templatext.template.tag;

import org.templatext.template.Parser;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateTagParser;
import org.templatext.template.compiler.Token;
import org.templatext.template.core.NodeList;

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
