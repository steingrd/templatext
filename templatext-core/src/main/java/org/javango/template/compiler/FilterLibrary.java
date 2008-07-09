package org.javango.template.compiler;

import org.javango.template.TemplateFilter;
import org.javango.template.filter.CapitalizeFirstFilter;
import org.javango.template.filter.CutFilter;
import org.javango.template.filter.DefaultFilter;
import org.javango.template.filter.LengthFilter;
import org.javango.template.filter.LowerFilter;
import org.javango.template.filter.UpperFilter;

public class FilterLibrary extends AbstractLibrary<TemplateFilter> {
	
	@Override
	protected void initialize() {
		register("capfirst", new CapitalizeFirstFilter());
		register("cut", new CutFilter());
		register("default", new DefaultFilter());
		register("length", new LengthFilter());
		register("lower", new LowerFilter());
		register("upper", new UpperFilter());
	}
	
}
