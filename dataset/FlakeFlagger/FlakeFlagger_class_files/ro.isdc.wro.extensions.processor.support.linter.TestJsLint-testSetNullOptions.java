/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.extensions.processor.support.linter;

import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestJsLint {
  private final JsLint jsLint = new JsLint();

  @Test
  public void testSetNullOptions()
    throws Exception {
    final String options = null;
    jsLint.setOptions(options);
    jsLint.validate("");
  }
}
