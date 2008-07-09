package org.templatext.template.core;

import org.templatext.template.Context;
import org.templatext.template.TemplateNode;

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
