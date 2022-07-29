package ro.isdc.wro.extensions.processor;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.extensions.processor.js.EmberJsProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test EmberJs processor.
 *
 * @author blemoine
 */
public class TestEmberJsProcessor {
  private ResourcePreProcessor processor;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    processor = new EmberJsProcessor();
  }

  @Test
  public void shouldTransformFilesFromFolder()
      throws IOException {
    final URL url = getClass().getResource("emberjs");
    final File testFolder = new File(url.getFile(), "test");
    final File expectedFolder = new File(url.getFile(), "expected");

    WroTestUtils.compareFromDifferentFoldersByExtension(testFolder, expectedFolder, "embbars", processor);
  }
}
