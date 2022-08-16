/*
 * Copyright (c) 2011. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.Callable;

import org.junit.Test;

import ro.isdc.wro.extensions.processor.js.JsLintProcessor;
import ro.isdc.wro.extensions.processor.support.linter.LinterException;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test {@link JsLintProcessor}.
 *
 * @author Alex Objelean
 */
public class TestJsLintProcessor
    extends AbstractTestLinterProcessor {
  @Test(expected = LinterException.class)
  public void testWithOptionsSet()
      throws Exception {
    final ResourcePostProcessor processor = new JsLintProcessor() {
      @Override
      protected void onLinterException(final LinterException e, final Resource resource) {
        throw e;
      };
    }.setOptionsAsString("maxerr=1");

    processor.process(new StringReader("alert(;"), new StringWriter());
  }
}
