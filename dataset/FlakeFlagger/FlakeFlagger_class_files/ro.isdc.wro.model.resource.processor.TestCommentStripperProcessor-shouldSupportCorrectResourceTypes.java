/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.impl.CommentStripperProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * TestCommentStripperProcessor.java.
 *
 * @author Ivar Conradi Østhus
 */
public class TestCommentStripperProcessor {
  private final ResourcePreProcessor processor = new CommentStripperProcessor();

  @Test public void shouldSupportCorrectResourceTypes(){WroTestUtils.assertProcessorSupportResourceTypes(processor,ResourceType.CSS,ResourceType.JS);}
}
