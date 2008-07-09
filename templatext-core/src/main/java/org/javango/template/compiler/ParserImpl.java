package org.javango.template.compiler;

import java.util.ArrayList;
import java.util.List;

import org.javango.template.Parser;
import org.javango.template.TemplateException;
import org.javango.template.core.NodeList;

public class ParserImpl implements Parser {

	private TokenStream tokenStream;
	private TagCompiler tagCompiler;

	public ParserImpl(String templateString, TagLibrary tagLibrary, FilterLibrary filterLibrary) {
		tokenStream = new TokenStream(new Lexer().tokenize(templateString));
		tagCompiler = new TagCompiler(tagLibrary, filterLibrary);
	}

	public NodeList parse() {
		NodeList block = new NodeList();
		while (tokenStream.hasNext()) {
			Token token = tokenStream.next();
			block.add(tagCompiler.compile(token, this));
		}
		return block;
	}
	
	public NodeList parse(String until) {
		List<String> untilList = new ArrayList<String>();
		untilList.add(until);
		return parse(untilList);
	}
	
	public NodeList parse(String[] until) {
		List<String> untilList = new ArrayList<String>();
		for (String string : until) {
			untilList.add(string);
		}
		return parse(untilList);
	}
	
	public NodeList parse(List<String> until) {
		NodeList block = new NodeList();
		boolean found = false;
		while (tokenStream.hasNext()) {
			Token t = tokenStream.peek();
			if (t.getType() == TokenType.TAG && until.contains(t.splitContents()[0])) {
				found = true;
				break;
			}
			tokenStream.next(); // advance the stream
			block.add(tagCompiler.compile(t, this));
		}
		
		if (!found) {
			// TODO flatten list
			throw new TemplateException("expected '" + until + "' but reached the end of the template");
		}
		
		return block;
	}

	public TokenStream getTokenStream() {
		return tokenStream;
	}
	
}
