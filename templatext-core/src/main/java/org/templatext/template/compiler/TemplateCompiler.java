package org.templatext.template.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.templatext.template.Template;
import org.templatext.template.TemplateException;
import org.templatext.template.core.NodeList;

public class TemplateCompiler {

	private TagLibrary tagLibrary = new TagLibrary();
	private FilterLibrary filterLibrary = new FilterLibrary();
	
	public Template compile(String templateString) {
		ParserImpl parser = new ParserImpl(templateString, tagLibrary, filterLibrary);
		NodeList nodeList = parser.parse();
		return new Template(nodeList);
	}
	
	public Template compile(BufferedReader reader) {
		StringBuffer templateString = new StringBuffer();
		
		try {
			String line = reader.readLine();
			while (line != null && !"".equals(line)) {
				templateString.append(line);
				// readLine strips away the newline, we want to keep it to
				// preserve the format the template author used
				templateString.append("\n");
				line = reader.readLine();
			}
		} catch (IOException e) {
			throw new TemplateException(e.getMessage());
		}
		
		return compile(templateString.toString());
	}
	
	public Template compile(InputStream inputStream) {
		return compile(new BufferedReader(new InputStreamReader(inputStream)));
	}
	
}
