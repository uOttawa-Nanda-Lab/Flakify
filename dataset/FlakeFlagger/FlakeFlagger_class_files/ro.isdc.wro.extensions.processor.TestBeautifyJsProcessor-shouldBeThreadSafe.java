/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.extensions.processor.js.BeautifyJsProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * TestBeautifyJsProcessor.
 *
 * @author Alex Objelean
 * @created Created on Apr 21, 2010
 */
public class TestBeautifyJsProcessor {
  @Test
  public void shouldBeThreadSafe() throws Exception {
    final BeautifyJsProcessor processor = new BeautifyJsProcessor() {
      @Override
      protected void onException(final WroRuntimeException e) {
        throw e;
      }
    };
    final Callable<Void> task = new Callable<Void>() {
      public Void call() {
        try {
          processor.process(new StringReader("alert(1);"), new StringWriter());
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
        return null;
      }
    };
    WroTestUtils.runConcurrently(task);
  }
}
