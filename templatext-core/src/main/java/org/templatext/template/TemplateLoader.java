package org.templatext.template;

import java.io.IOException;

public interface TemplateLoader {

	Template load(String name) throws TemplateNotFoundException, IOException;
	
}
