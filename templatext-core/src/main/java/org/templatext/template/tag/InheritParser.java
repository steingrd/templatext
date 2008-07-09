package org.templatext.template.tag;

public class InheritParser extends AbstractSingleStringArgumentParser<InheritNode> {

	@Override
	protected InheritNode createNode(String argument) {
		return new InheritNode(argument);
	}
	
}
