package org.templatext.template.compiler;

import org.templatext.template.TemplateFilter;
import org.templatext.template.filter.CapitalizeFirstFilter;
import org.templatext.template.filter.CutFilter;
import org.templatext.template.filter.DefaultFilter;
import org.templatext.template.filter.LengthFilter;
import org.templatext.template.filter.LowerFilter;
import org.templatext.template.filter.UpperFilter;

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
