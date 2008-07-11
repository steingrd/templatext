package org.templatext.template;

/**
 * Template loaders should throw {@link TemplateNotFoundException} when the
 * template could not be loaded because the name was invalid.
 * <p>
 * {@link TemplateNotFoundException} does <b>not</b> inherit
 * {@link TemplateException} and is <b>not</b> a {@link RuntimeException}.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class TemplateNotFoundException extends Exception {

	/**
	 * Default constructor for creating a exception without a message.
	 */
	public TemplateNotFoundException() {
	}
	
	/**
	 * Constructor for creating a exception with a message. 
	 * @param message
	 */
	public TemplateNotFoundException(String message) {
		super(message);
	}

}
