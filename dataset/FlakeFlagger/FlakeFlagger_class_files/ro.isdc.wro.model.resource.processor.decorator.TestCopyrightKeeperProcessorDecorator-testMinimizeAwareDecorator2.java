/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.model.resource.processor.decorator;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssMinProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssUrlRewritingProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.util.WroTestUtils;

/**
 * @author Alex Objelean
 */
public class TestCopyrightKeeperProcessorDecorator {
  @Test public void testMinimizeAwareDecorator2(){final ResourcePreProcessor decoratedProcessor=new CssUrlRewritingProcessor();final ResourcePreProcessor processor=CopyrightKeeperProcessorDecorator.decorate(decoratedProcessor);Assert.assertFalse(new ProcessorDecorator(processor).isMinimize());}
}
