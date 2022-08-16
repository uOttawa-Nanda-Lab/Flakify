/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import static ro.isdc.wro.extensions.processor.support.uglify.UglifyJs.Type.UGLIFY;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.extensions.processor.js.UglifyJsProcessor;
import ro.isdc.wro.extensions.processor.support.uglify.UglifyJs;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * TestUglifyJsProcessor.
 *
 * @author Alex Objelean
 * @created Created on Apr 21, 2010
 */
public class TestUglifyJsProcessor {
  private File testFolder;


  @Before
  public void setUp() {
    testFolder = new File(ClassLoader.getSystemResource("test").getFile());
  }


  @Test
  public void shouldBeThreadSafe()
    throws Exception {
    final UglifyJsProcessor processor = new UglifyJsProcessor() {
      @Override
      protected void onException(final WroRuntimeException e) {
        throw e;
      }
    };
    final Callable<Void> task = new Callable<Void>() {
      @Override
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
