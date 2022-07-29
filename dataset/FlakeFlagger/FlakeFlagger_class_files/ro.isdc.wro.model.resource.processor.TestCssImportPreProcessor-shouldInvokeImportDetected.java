/*
 * Copyright (c) 2009. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.config.support.ContextPropagatingCallable;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test for css import processor.
 *
 * @author Alex Objelean
 */
public class TestCssImportPreProcessor {
  private ResourcePreProcessor victim;

  @Before
  public void setUp() {
    final WroConfiguration config = new WroConfiguration();
    config.setIgnoreFailingProcessor(true);
    Context.set(Context.standaloneContext(), config);
    victim = new CssImportPreProcessor();
    WroTestUtils.initProcessor(victim);
  }

  private void processInvalidImport()
      throws IOException {
    final Resource resource = Resource.create("someResource.css");
    final Reader reader = new StringReader("@import('/path/to/invalid.css');");
    victim.process(resource, reader, new StringWriter());
  }

  @Test public void shouldInvokeImportDetected() throws IOException{final AtomicInteger times=new AtomicInteger();victim=new CssImportPreProcessor(){@Override protected void onImportDetected(final String foundImportUri){times.incrementAndGet();}};WroTestUtils.initProcessor(victim);final Resource resource=Resource.create("someResource.css");final Reader reader=new StringReader("@import('/path/to/invalid.css');");victim.process(resource,reader,new StringWriter());assertEquals(1,times.get());}

  void assertImportDetected(final String content, final String expected)
      throws IOException {
    final AtomicBoolean found = new AtomicBoolean();
    victim = new CssImportPreProcessor() {
      @Override
      protected void onImportDetected(final String foundImportUri) {
        assertEquals(expected, foundImportUri);
        found.set(true);
      }
      @Override
      protected String doTransform(final String cssContent, final List<Resource> foundImports)
          throws IOException {
        return "";
      }
    };
    WroTestUtils.initProcessor(victim);
    victim.process(Resource.create("/css/parent.css"), new StringReader(content), new StringWriter());
    assertTrue(found.get());
  }
}
