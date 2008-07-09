package org.javango.template.compiler;

import org.javango.template.TemplateTagParser;
import org.javango.template.tag.CycleParser;
import org.javango.template.tag.ForParser;
import org.javango.template.tag.IfParser;
import org.javango.template.tag.InheritParser;
import org.javango.template.tag.OverrideParser;

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
