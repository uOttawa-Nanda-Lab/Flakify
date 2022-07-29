/*
 * Copyright (c) 2011. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.extensions.processor.js.CJsonProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test cjson processor.
 *
 * @author Alex Objelean
 * @created Created on June 07, 2011
 */
public class TestCJsonProcessor {

  @Test
  public void shouldBeThreadSafe() throws Exception {
    genericThreadSafeTest(true);
    genericThreadSafeTest(false);
  }

  private void genericThreadSafeTest(boolean pack)
      throws Exception {
    final CJsonProcessor processor = new CJsonProcessor(pack) {
      @Override
      protected void onException(final WroRuntimeException e) {
        throw e;
      }
    };
    final Callable<Void> task = new Callable<Void>() {
      public Void call() {
        try {
          processor.process(new StringReader("{\"p\" : 1}"), new StringWriter());
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
        return null;
      }
    };
    WroTestUtils.runConcurrently(task, 20);
  }
}
