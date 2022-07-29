/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.extensions.processor.css.RubySassCssProcessor;
import ro.isdc.wro.extensions.processor.support.sass.RubySassEngine;
import ro.isdc.wro.util.Function;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test ruby sass css processor.
 *
 * @author Simon van der Sluis
 * @created Created on Apr 21, 2010
 */
public class TestRubySassCssProcessor {
  /** Location (base) of ruby sass css test resources. */
  private final URL url = getClass().getResource("rubysasscss");

  /** A RubySassEngine to test */
  private RubySassCssProcessor processor;

  @Before
  public void setUp() {
    processor = new RubySassCssProcessor();
  }

  @Test
  public void shouldBeThreadSafe()
      throws Exception {
    final Callable<Void> task = new Callable<Void>() {
      @Override
      public Void call() {
        try {
          processor.process(new StringReader(
              "$side: top;$radius: 10px;.rounded-#{$side} {border-#{$side}-radius: $radius;}"), new StringWriter());
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
        return null;
      }
    };
    WroTestUtils.runConcurrently(task);
  }

}
