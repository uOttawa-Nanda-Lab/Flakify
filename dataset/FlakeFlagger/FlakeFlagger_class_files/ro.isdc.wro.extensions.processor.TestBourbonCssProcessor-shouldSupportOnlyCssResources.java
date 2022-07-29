/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ro.isdc.wro.extensions.processor.css.BourbonCssProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test BourbonCssProcessor.
 * 
 * @author Simon van der Sluis
 * @created Created on Apr 21, 2010
 */
public class TestBourbonCssProcessor {
  
  /** Location (base) of bourbon sass css with bourbon test resources. */
  private final URL url = getClass().getResource("bourboncss");
  
  /** A RubySassEngine to test */
  private BourbonCssProcessor processor;
  
  @Before
  public void initEngine() {
    processor = new BourbonCssProcessor();
  }
  
  @Test
  public void shouldSupportOnlyCssResources() {
    WroTestUtils.assertProcessorSupportResourceTypes(processor, ResourceType.CSS);
  }
}
