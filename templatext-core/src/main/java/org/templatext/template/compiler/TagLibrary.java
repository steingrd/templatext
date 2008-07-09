package org.templatext.template.compiler;

import org.templatext.template.TemplateTagParser;
import org.templatext.template.tag.CycleParser;
import org.templatext.template.tag.ForParser;
import org.templatext.template.tag.IfParser;
import org.templatext.template.tag.InheritParser;
import org.templatext.template.tag.OverrideParser;

public class TagLibrary extends AbstractLibrary<TemplateTagParser> {

	@Override
	protected void initialize() {
		register("cycle", new CycleParser());
		register("for", new ForParser());
		register("if", new IfParser());
		register("inherit", new InheritParser());
		register("override", new OverrideParser());
	}
	
}
