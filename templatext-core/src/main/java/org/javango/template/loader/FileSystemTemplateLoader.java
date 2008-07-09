package org.javango.template.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.javango.template.Template;
import org.javango.template.TemplateException;
import org.javango.template.TemplateLoader;
import org.javango.template.TemplateNotFoundException;

public class FileSystemTemplateLoader implements TemplateLoader {

	private String basePath;
	
	public FileSystemTemplateLoader() {
	}
	
	public FileSystemTemplateLoader(File directory) {
		this.basePath = directory.getAbsolutePath();
		if (!this.basePath.endsWith(File.separator)) {
			this.basePath += File.separator;
		}
	}

	public Template load(String name) throws TemplateException {
		name = this.basePath + name;
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			throw new TemplateNotFoundException(e.getMessage()); 
		}
		
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
		
		return new Template(templateString.toString());
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}
