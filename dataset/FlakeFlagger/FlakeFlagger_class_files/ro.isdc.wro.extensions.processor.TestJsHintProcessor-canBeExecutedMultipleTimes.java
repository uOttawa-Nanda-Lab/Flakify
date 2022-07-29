/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.extensions.processor.js.JsHintProcessor;
import ro.isdc.wro.extensions.processor.support.linter.LinterException;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test {@link JsHintProcessor}.
 *
 * @author Alex Objelean
 * @created Created on Feb 27, 2011
 */
public class TestJsHintProcessor
    extends AbstractTestLinterProcessor {
  private JsHintProcessor victim;

  @Before
  public void setUp() {
    victim = new JsHintProcessor();
  }

  /**
 * This test was created initially to prove that   {@link JsHintProcessor}  is thread-safe, but it doesn't work well whentrying to reuse the scope. TODO: This needs to be investigated.
 */@Test public void canBeExecutedMultipleTimes() throws Exception{final JsHintProcessor processor=new JsHintProcessor();final Callable<Void> task=new Callable<Void>(){@Override public Void call(){try {processor.process(new StringReader("alert(1);"),new StringWriter());} catch (final Exception e){throw new RuntimeException(e);}return null;}};WroTestUtils.runConcurrently(task);}
}
