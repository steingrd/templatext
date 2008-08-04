package org.templatext.template.utils;

import junit.framework.TestCase;

public class QuotedStringTokenizerTest extends TestCase {

	private QuotedStringTokenizer tokenizer;
	private String[] result;
	
	public void testTokenizeSimpleTokens() throws Exception {
		tokenizer = new QuotedStringTokenizer("one two three");
		result = tokenizer.allTokens();
		
		assertEquals(3, result.length);
		assertEquals("one", result[0]);
		assertEquals("two", result[1]);
		assertEquals("three", result[2]);
	}
	
	public void testSimpleTokensMultipleWhitespace() throws Exception {
		tokenizer = new QuotedStringTokenizer("one   two  \t three");
		result = tokenizer.allTokens();
		
		assertEquals(3, result.length);
		assertEquals("one", result[0]);
		assertEquals("two", result[1]);
		assertEquals("three", result[2]);
	}
	
	public void testSimpleTokensWhitespaceAtStartAndEnd() throws Exception {
		tokenizer = new QuotedStringTokenizer("   one   two  \t three   ");
		result = tokenizer.allTokens();
		
		assertEquals(3, result.length);
		assertEquals("one", result[0]);
		assertEquals("two", result[1]);
		assertEquals("three", result[2]);
	}
	
	public void testQuoutedToken() throws Exception {
		tokenizer = new QuotedStringTokenizer("one 'two three'");
		result = tokenizer.allTokens();
		
		assertEquals(2, result.length);
		assertEquals("one", result[0]);
		assertEquals("two three", result[1]);
	}
}
