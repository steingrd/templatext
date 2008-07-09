package org.javango.template.tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.javango.template.Context;
import org.javango.template.TemplateException;
import org.javango.template.TemplateNode;
import org.javango.template.Variable;
import org.javango.template.core.NodeList;

/**
 * For loops takes a for loop variable name and a sequence and iterates over the
 * sequence, yielding a new value in the given variable name in the context for
 * each iteration.
 * 
 * Syntax: 
 * 
 *   {% for value in sequence %} ... {% endfor %}
 * 
 * The variable name "sequence" must resolve to a object in the given context
 * that implements the Iterable interface.
 * 
 * TODO nested for loops
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class ForNode implements TemplateNode {

	private Variable iterable;
	private String loopVariableName;
	private NodeList body;
	
	public ForNode(String loopVariableName, String iterableVariableName, NodeList body) {
		this.iterable = new Variable(iterableVariableName);
		this.loopVariableName = loopVariableName;
		this.body = body;
	}

	@SuppressWarnings("unchecked")
	public String render(Context context) {
		Object sequence = iterable.resolve(context);
		Iterator iterator = getIteratorFromObject(sequence);
		
		if (iterator == null) {
			throw new TemplateException("for loop sequence variable is not iterable, type is " + sequence.getClass().getName());
		}
		
		return renderInternal(context, iterator);
	}

	@SuppressWarnings("unchecked")
	private String renderInternal(Context context, Iterator iterator) {
		StringBuilder result = new StringBuilder();
		
		Map<String,Object> forloop = createForloopVariable();
		context.put("forloop", forloop);
		boolean first = true;
		boolean last = false;
		
		while (iterator.hasNext()) {
			context.put(loopVariableName, iterator.next());
			
			last = !iterator.hasNext();
			forloop.put("last", last);
			
			result.append(body.render(context));
			updateForloopVariable(forloop);
			
			if (first) {
				forloop.put("first", false);
			}
		}
		
		context.remove("forloop");
		return result.toString();
	}

	private void updateForloopVariable(Map<String, Object> forloop) {
		forloop.put("counter", ((Integer)forloop.get("counter")).intValue() + 1);
		forloop.put("counter0", ((Integer)forloop.get("counter0")).intValue() + 1);
	}

	private Map<String, Object> createForloopVariable() {
		Map<String, Object> forloop = new HashMap<String, Object>();
		forloop.put("counter", 1);
		forloop.put("counter0", 0);
		forloop.put("first", true);
		forloop.put("last", false);
		return forloop;
	}

	@SuppressWarnings("unchecked")
	private Iterator getIteratorFromObject(Object sequence) {
		Iterator iterator = null;
		if (sequence.getClass().isArray()) {
			iterator = new ArrayIterator(sequence);
		} else {
			if (sequence instanceof Iterable) {
				iterator = ((Iterable)sequence).iterator();
			} else if (sequence instanceof Iterator) {
				iterator = (Iterator)sequence;
			}
		}
		return iterator;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private boolean isIterable(Object sequence) {
		Class[] interfaces = sequence.getClass().getInterfaces();
		for (Class clazz : interfaces) {
			if (clazz.equals(Iterable.class)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "<ForNode: " + iterable + ">";
	}
}
