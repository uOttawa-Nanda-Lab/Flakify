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

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.extensions.processor.css.CssLintProcessor;
import ro.isdc.wro.extensions.processor.support.csslint.CssLintException;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.util.Transformers;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test {@link CssLintProcessor}.
 *
 * @author Alex Objelean
 */
public class TestCssLintProcessor {
  private CssLintProcessor victim;

  @Before
  public void setUp() {
    victim = new CssLintProcessor();
  }

  private String createValidCss() {
    return ".label {color: red;}";
  }

  @Test(expected=CssLintException.class) public void shouldOverrideDefaultOptions() throws Exception{victim=new CssLintProcessor(){@Override protected String createDefaultOptions(){return "import";}@Override protected void onCssLintException(final CssLintException e,final Resource resource){throw e;}};victim.process(null,new StringReader("@import url(more.css);"),new StringWriter());}
}
