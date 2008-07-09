package org.templatext.template.compiler;

/**
 * Enum for the valid template token types. 
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public enum TokenType {
	
	/**
	 * Denotes a template text, i.e. something not enclosed in template
	 * markers; text between nodes.
	 */
	TEXT, 
	
	/**
	 * Denotes a template variable, i.e. something enclosed in {{ and }}.
	 */
	VARIABLE, 
	
	/**
	 * Denotes a template tag, i.e. something enclosed in {% and %}.
	 */
	TAG, 
	
	/**
	 * Denotes a template comment, i.e. something enclosed in {# and #}
	 */
	COMMENT ;
	
}