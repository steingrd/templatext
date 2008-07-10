package org.templatext.template.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.templatext.template.Template;
import org.templatext.template.TemplateLoader;
import org.templatext.template.TemplateNotFoundException;
import org.templatext.template.compiler.TemplateCompiler;

public class FileSystemTemplateLoader implements TemplateLoader {

	private String basePath;
	private TemplateCompiler compiler = new TemplateCompiler();
	
	public FileSystemTemplateLoader() {
	}
	
	public FileSystemTemplateLoader(File directory) {
		this.basePath = directory.getAbsolutePath();
		if (!this.basePath.endsWith(File.separator)) {
			this.basePath += File.separator;
		}
	}

	public Template load(String name) throws TemplateNotFoundException {
		name = this.basePath + name;
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			throw new TemplateNotFoundException(e.getMessage()); 
		}
		
		return compiler.compile(reader);
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}
