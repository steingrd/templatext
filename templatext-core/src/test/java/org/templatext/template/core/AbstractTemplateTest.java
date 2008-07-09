package org.templatext.template.core;

import junit.framework.TestCase;

import org.templatext.template.Context;
import org.templatext.template.Template;

/**
 * Base class for template tests. Defines methods for rendering and templates
 * and asserting results.
 * 
 * @author Steingrim Dovland <steingrd@ifi.uio.no>
 */
public abstract class AbstractTemplateTest extends TestCase {

	protected static void renderAndAssert(String name, String template, Context context, String expected) {
		Template t = new Template(template);
		assertEquals(expected, t.render(context));
	}

	protected static void renderAndAssert(String name, String template, Context context, Exception exception) {
		Template t = new Template(template);
		try {
			t.render(context);
			fail("expected exception: " + exception.getClass().getName());
		} catch (Exception e) {
			assertEquals(exception.getClass(), e.getClass());
		}
	}
	
	protected static Context createContext(Object ... args) {
		Context context = new Context();
		// iterate over the args array, notice +=2 instead of ++
		for (int i = 0; i < args.length; i += 2) {
			context.put((String) args[i], args[i+1]);
		}
		return context;
	}
	
}
