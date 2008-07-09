package org.javango.template.core;

import org.javango.template.Context;
import org.javango.template.TemplateNode;

public class TextNode implements TemplateNode {

	private String text;
	
	public TextNode(String text) {
		this.text = text;
	}

	public String render(Context context) {
		return text != null ? text : "";
	}

	@Override
	public String toString() {
		return "<TextNode: " + text.length() + " characters>";
	}
}
