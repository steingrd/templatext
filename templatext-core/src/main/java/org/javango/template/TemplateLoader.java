package org.javango.template;

import java.io.IOException;

public interface TemplateLoader {

	Template load(String name) throws TemplateException, IOException;
	
}
