package org.templatext.template.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.templatext.template.Context;
import org.templatext.template.TemplateNode;
import org.templatext.template.tag.InheritNode;

public class NodeList implements TemplateNode, Iterable<TemplateNode> {

	private List<TemplateNode> nodes = new ArrayList<TemplateNode>();
	
	public String render(Context context) {
		StringBuilder block = new StringBuilder();
		for (TemplateNode node : nodes) {
			block.append(node.render(context));
			
			if (node.getClass().equals(InheritNode.class)) {
				break;
			}
		}
		return block.toString();
	}
	
	public void add(TemplateNode templateNode) {
		this.nodes.add(templateNode);
	}
	
	@Override
	public String toString() {
		return "<BlockNode: " + nodes.size() + " nodes>";
	}

	public Iterator<TemplateNode> iterator() {
		return nodes.iterator();
	}

}
