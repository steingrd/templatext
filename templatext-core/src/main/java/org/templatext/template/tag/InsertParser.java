package org.templatext.template.tag;

public class InsertParser extends AbstractSingleStringArgumentParser<InsertNode> {

	@Override
	protected InsertNode createNode(String argument) {
		return new InsertNode(argument);
	}
	
}
