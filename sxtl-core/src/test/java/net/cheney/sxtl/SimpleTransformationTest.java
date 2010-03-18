package net.cheney.sxtl;

import static junit.framework.Assert.assertEquals;
import net.cheney.snax.model.Attribute;
import net.cheney.snax.model.Element;

import org.junit.Test;


public class SimpleTransformationTest {

	@Test public void testSimpleTransformation() {
		Element source = new Element("foo");
		Element result = (Element) new Transformer().transform(source).iterator().next();
		assertEquals(source, result);
	}
	
	@Test public void testSimpleTransformation2() {
		Element source = new Element("foo", new Attribute("bar", "baz"));
		Element result = (Element) new Transformer().transform(source).iterator().next();
		assertEquals(source, result);
	}
}
