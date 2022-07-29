/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.impl.js.SemicolonAppenderPreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test for {@link SemicolonAppenderPreProcessor} class.
 *
 * @author Alex Objelean
 * @created Created on March 21, 2010
 */
public class TestSemicolonAppenderPreProcessor {
  @Test
  public void shouldSupportCorrectResourceTypes() {
    WroTestUtils.assertProcessorSupportResourceTypes(new SemicolonAppenderPreProcessor(), ResourceType.JS);
  }
}
