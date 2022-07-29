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
  public void shouldValidateWithMultipleOptions()
    throws Exception {
    jsLint.setOptions("undef,eqeqeq");
    jsLint.validate("");
  }
}
