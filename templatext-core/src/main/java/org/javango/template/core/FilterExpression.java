package org.javango.template.core;

import org.javango.template.Context;
import org.javango.template.TemplateSyntaxErrorException;
import org.javango.template.Variable;


/**
 * A filter expression is a variable expression with zero or more filters applied.
 * For instance, the expression
 * 
 *    myvar|lower|capfirst
 *    
 * is a chained filter expression. The filter `lower` is applied to the variable `myvar`
 * and the result is passed through the `capfirst` filter. The result can be obtained 
 * using the `evaluate` method of the compiled filter expression.
 * 
 * A much simpler filter expression is that which is only a variable:
 * 
 *    myvar
 *    
 * This filter expression returns the variable when evaluated. 
 * 
 * Use a FilterExpressionCompiler to get a compiled FilterExpression from a String.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public class FilterExpression {
	
	private Variable variable;
	private FilterChain chain;
	
	public FilterExpression(String variable) {
		this(variable, null);
	}
	
	public FilterExpression(String variable, FilterChain chain) {
		this.variable = new Variable(variable);
		this.chain = chain;
	}
	
	/**
	 * Evalutes the filter expression in the given context and returns the result as
	 * an Object. 
	 * 
	 * If the expression is without filters, the value of the variable in the given
	 * context is returned.
	 * 
	 * If the expression contains any filters the variable is passed through first 
	 * filter and then the result of this is passed down the filter chain. The value 
	 * returned by the last filter in the chain is then returned.
	 * 
	 * @param context the context to evaluate in
	 * @return a Object result of the filter expression
	 * @throws TemplateSyntaxErrorException 
	 */
	public Object evaluate(Context context) {
		Object object = variable.resolve(context);
		return chain == null ? object : chain.apply(object);
	}

	public Variable getVariable() {
		return variable;
	}

	public FilterChain getChain() {
		return chain;
	}
	
	@Override
	public String toString() {
		return "<FilterExpression: " + variable.getName() + ">";
	}
	
}
