/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.model.resource.processor.decorator;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssUrlRewritingProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestExtensionsAwareProcessorDecorator {
  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
  }

  @Test
  public void testMinimizeAwareDecorator2() {
    final ResourcePreProcessor decoratedProcessor = new CssUrlRewritingProcessor();
    final ResourcePreProcessor processor = ExtensionsAwareProcessorDecorator.decorate(decoratedProcessor);
    Assert.assertFalse(new ProcessorDecorator(processor).isMinimize());
  }
}
