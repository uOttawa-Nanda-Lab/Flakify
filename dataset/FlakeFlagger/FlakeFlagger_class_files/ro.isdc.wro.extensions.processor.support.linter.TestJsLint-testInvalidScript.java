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

  @Test(expected=LinterException.class) public void testInvalidScript() throws Exception{jsLint.validate("al ert(1)");}
}
