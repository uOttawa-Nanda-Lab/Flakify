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
import ro.isdc.wro.extensions.processor.js.HandlebarsJsProcessor;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test Handlebars.js processor.
 *
 * @author heldeen
 */
public class TestHandlebarsJsProcessor {
  private ResourcePreProcessor processor;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    processor = new HandlebarsJsProcessor();
  }

  @Test
  public void testSimpleString()
      throws Exception {
    final StringWriter writer = new StringWriter();
    processor.process(null, new StringReader("Hello {name}!"), writer);
    final String result = writer.toString();
    assertTrue(result.contains("return \"Hello {name}!\\n\";"));
  }
}
