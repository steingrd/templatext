package org.javango.template.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.javango.template.Context;
import org.javango.template.TemplateNode;
import org.javango.template.tag.InheritNode;

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
