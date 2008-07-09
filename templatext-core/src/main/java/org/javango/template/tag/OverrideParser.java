package org.javango.template.tag;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.TemplateTagParser;
import org.javango.template.compiler.Token;
import org.javango.template.core.NodeList;

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
