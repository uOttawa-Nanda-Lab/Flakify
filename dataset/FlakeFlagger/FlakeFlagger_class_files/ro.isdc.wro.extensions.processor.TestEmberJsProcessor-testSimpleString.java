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
  public void testSimpleString()
      throws Exception {
    final StringWriter writer = new StringWriter();
    processor.process(null, new StringReader("Hello {name}!"), writer);
    final String result = writer.toString();
    assertTrue(result.startsWith("(function() {Ember.TEMPLATES["));
    assertTrue(result.contains("data.buffer.push(\"Hello {name}!\\n\");"));
  }
}
