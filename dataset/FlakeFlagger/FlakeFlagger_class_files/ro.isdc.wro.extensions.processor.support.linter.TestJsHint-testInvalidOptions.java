/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.extensions.processor.support.linter;

import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;


/**
 * @author Alex Objelean
 */
public class TestJsHint {
  private static final String VALID_JS = "alert(1);";
  private final JsHint jsHint = new JsHint();

  @Test(expected=WroRuntimeException.class) public void testInvalidOptions() throws Exception{jsHint.setOptions("unused:vars");jsHint.validate("function test() {\n  alert(1);\n}");}
}
