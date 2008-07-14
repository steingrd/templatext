package org.templatext.template.compiler;

import java.io.BufferedReader;
import java.io.StringReader;

import org.templatext.template.Context;
import org.templatext.template.Template;

import junit.framework.TestCase;

public class TemplateCompilerTest extends TestCase {

	private TemplateCompiler compiler;
	private Context context;
	
	protected void setUp() throws Exception {
		compiler = new TemplateCompiler();
		context = Context.create();
	}
	
	public void testCompileFromStringGeneratesValidTemplate() throws Exception {
		context.put("var1", "foo");
		context.put("var2", "bar");
		
		String templateString = "\n\n{{ var1 }}\n\n{{ var2 }}\n\n";
		Template template = compiler.compile(templateString);
		
		assertEquals("\n\nfoo\n\nbar\n\n", template.render(context));
	}
	
	public void testCompilerFromBufferedReaderGeneratesValidTemplate() throws Exception {
		context.put("var1", "foo");
		context.put("var2", "bar");
		
		String templateString = "\n\n{{ var1 }}\n\n{{ var2 }}\n\n";
		BufferedReader bufferedReader = new BufferedReader(new StringReader(templateString));
		
		Template template = compiler.compile(bufferedReader);
		
		assertEquals("\n\nfoo\n\nbar\n\n", template.render(context));
	}

}
