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

  /**
 * Test that processing invalid sass css produces exceptions.
 */@Test public void shouldFailWhenInvalidSassCssIsProcessed() throws Exception{final File testFolder=new File(url.getFile(),"invalid");WroTestUtils.forEachFileInFolder(testFolder,new Function<File, Void>(){@Override public Void apply(final File input) throws Exception{try {processor.process(null,new FileReader(input),new StringWriter());fail("Shouldn't have failed");} catch (final WroRuntimeException e){}return null;}});}

}
