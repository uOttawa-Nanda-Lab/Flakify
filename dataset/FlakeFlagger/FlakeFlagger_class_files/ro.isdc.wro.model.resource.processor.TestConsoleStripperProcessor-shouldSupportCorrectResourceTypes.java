/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.impl.js.ConsoleStripperProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * TestMultiLineCommentStripperPostProcessor.java.
 *
 * @author Alex Objelean
 */
public class TestConsoleStripperProcessor {
  private final ResourcePreProcessor processor = new ConsoleStripperProcessor();

  @Test
  public void shouldSupportCorrectResourceTypes() {
    WroTestUtils.assertProcessorSupportResourceTypes(processor, ResourceType.JS);
  }
}
