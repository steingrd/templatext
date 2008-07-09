package org.javango.template;

public class TemplateSyntaxErrorException extends TemplateException {

	public TemplateSyntaxErrorException(String message) {
		super(message);
	}

	
	public TemplateSyntaxErrorException(String message, Exception inner) {
		super(message, inner);
	}
	
}
