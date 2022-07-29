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

  @Test public void shouldAcceptBadOptions() throws Exception{jsLint.setOptions("1,2");jsLint.validate("");}
}
