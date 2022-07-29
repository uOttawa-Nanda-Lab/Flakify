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

  @Test(expected=LinterException.class) public void shouldValidateWithEqeqeqOption() throws Exception{jsHint.setOptions("eqeqeq");jsHint.validate("var j = 1;var i = j == 0 ? j + 1 : j - 1;");}
}
