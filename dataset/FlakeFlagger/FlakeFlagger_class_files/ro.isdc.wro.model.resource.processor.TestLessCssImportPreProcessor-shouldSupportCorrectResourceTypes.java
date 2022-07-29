/*
 * Copyright (c) 2009. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.impl.css.LessCssImportPreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestLessCssImportPreProcessor {
  private ResourcePreProcessor victim;

  @Before
  public void setUp() {
    final WroConfiguration config = new WroConfiguration();
    config.setIgnoreFailingProcessor(true);
    Context.set(Context.standaloneContext(), config);
    victim = new LessCssImportPreProcessor();
    WroTestUtils.initProcessor(victim);
  }

  @Test
  public void shouldSupportCorrectResourceTypes() {
    WroTestUtils.assertProcessorSupportResourceTypes(victim, ResourceType.CSS);
  }
}
