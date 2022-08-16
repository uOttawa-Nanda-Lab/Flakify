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
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.extensions.processor.js.RhinoCoffeeScriptProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.ExceptionHandlingProcessorDecorator;
import ro.isdc.wro.util.WroTestUtils;

/**
 * Test CoffeeScript processor.
 *
 * @author Alex Objelean
 * @since 1.3.6
 * @created Created on Mar 26, 2011
 */
public class TestRhinoCoffeeScriptProcessor {
  private ResourcePreProcessor processor;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    processor = new RhinoCoffeeScriptProcessor();
  }

  /**
   * Test that by default, failing to process a js with coffeeScript, will leave the result unchanged.
   */
  @Test
  public void testExceptions()
    throws IOException {
    final URL url = getClass().getResource("coffeeScript/exceptions");
    final AtomicInteger counter = new AtomicInteger();
    processor = new ExceptionHandlingProcessorDecorator(new RhinoCoffeeScriptProcessor() {
      @Override
      protected void onException(final Exception e) {
        counter.incrementAndGet();
        super.onException(e);
      }
    }) {
      @Override
      protected boolean isIgnoreFailingProcessor() {
        return true;
      }
    };

    final File testFolder = new File(url.getFile(), "test");
    final File expectedFolder = new File(url.getFile(), "expected");
    WroTestUtils.compareFromDifferentFoldersByExtension(testFolder, expectedFolder, "js",
      processor);
    Assert.assertEquals(2, counter.get());
  }
}
