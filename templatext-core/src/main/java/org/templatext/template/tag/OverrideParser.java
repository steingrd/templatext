package org.templatext.template.tag;

import org.templatext.template.Parser;
import org.templatext.template.TemplateException;
import org.templatext.template.TemplateNode;
import org.templatext.template.TemplateTagParser;
import org.templatext.template.compiler.Token;
import org.templatext.template.core.NodeList;

/**
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class OverrideParser implements TemplateTagParser {

	public TemplateNode parse(Parser parser, Token token) {
		String[] contents = token.splitContents();
		if (contents.length != 2) {
			throw new TemplateException("missing override name");
		}
		
		NodeList block = parser.parse("endoverride");
		
		Token next = parser.getTokenStream().next(); // advance past the endoverride tag
		assert next.splitContents()[0].equals("endoverride");
		
		return new OverrideNode(contents[1], block);
	}

}
