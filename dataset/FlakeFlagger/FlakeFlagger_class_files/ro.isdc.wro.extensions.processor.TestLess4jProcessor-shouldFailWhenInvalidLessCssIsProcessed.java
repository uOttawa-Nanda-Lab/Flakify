/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.extensions.processor.css.Less4jProcessor;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.LessCssImportPreProcessor;
import ro.isdc.wro.model.resource.processor.support.ChainedProcessor;
import ro.isdc.wro.util.Function;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test less css processor based on lessc shell which uses node.
 *
 * @author Alex Objelean
 */
public class TestLess4jProcessor {
  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
  }

  /**
 * Test that processing invalid less css produces exceptions
 */@Test public void shouldFailWhenInvalidLessCssIsProcessed() throws Exception{final ResourcePreProcessor processor=new Less4jProcessor();final URL url=getClass().getResource("lesscss");final File testFolder=new File(url.getFile(),"invalid");WroTestUtils.forEachFileInFolder(testFolder,new Function<File, Void>(){@Override public Void apply(final File input) throws Exception{try {processor.process(Resource.create(input.getPath(),ResourceType.CSS),new FileReader(input),new StringWriter());Assert.fail("Expected to fail, but didn't");} catch (final Exception e){}return null;}});}
}
