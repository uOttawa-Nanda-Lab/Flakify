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

  @Test
  public void testSetNullOptions()
      throws Exception {
    final String options = null;
    jsHint.setOptions(options);
    jsHint.validate(VALID_JS);
  }
}
