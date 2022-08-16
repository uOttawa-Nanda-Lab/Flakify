/*
 * Copyright (c) 2011. All rights reserved.
 */
package ro.isdc.wro.extensions.processor;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.Test;

import ro.isdc.wro.extensions.processor.js.JsonHPackProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test json hpack processor.
 *
 * @author Alex Objelean
 * @created Created on June 07, 2011
 */
public class TestJsonHPackProcessor {

  @Test
  public void testUnpackFromFolder()
      throws Exception {
    final ResourcePostProcessor processor = JsonHPackProcessor.unpackProcessor();
    final URL url = getClass().getResource("jsonhpack");

    final File testFolder = new File(url.getFile(), "pack");
    final File expectedFolder = new File(url.getFile(), "unpack");
    WroTestUtils.compareFromDifferentFoldersByExtension(testFolder, expectedFolder, "js", processor);
  }


  private void genericThreadSafeTest(boolean pack)
      throws Exception {
    final ResourcePostProcessor processor = new JsonHPackProcessor(pack);
    final Callable<Void> task = new Callable<Void>() {
      public Void call() {
        try {
          processor.process(new StringReader("{p : 1}"), new StringWriter());
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
        return null;
      }
    };
    WroTestUtils.runConcurrently(task, 20);
  }
}
