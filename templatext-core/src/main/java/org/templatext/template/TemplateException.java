package org.templatext.template;

public class TemplateException extends RuntimeException {

	public TemplateException() {
		super();
	}

	public TemplateException(String message) {
		super(message);
	}
	
	public TemplateException(String message, Exception inner) {
		super(message, inner);
	}

}
