package ro.isdc.wro.model.resource.processor.decorator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.SupportedResourceType;
import ro.isdc.wro.model.resource.processor.Destroyable;
import ro.isdc.wro.model.resource.processor.ImportAware;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.SupportAware;
import ro.isdc.wro.model.resource.processor.impl.CommentStripperProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssMinProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssVariablesProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.SemicolonAppenderPreProcessor;

/**
 * @author Alex Objelean
 */
public class TestProcessorDecorator {
  @Mock
  private ResourcePreProcessor mockProcessor;

  @Before
  public void setUp() {
    initMocks(this);
  }

  private static class SupportAwareProcessor extends JSMinProcessor implements SupportAware {
   public boolean isSupported() {
      return true;
    }
  }

  private static class ImportAwareProcessor extends CssImportPreProcessor implements ImportAware {
    @Override
    public boolean isImportAware() {
      return true;
    }
  }

  private static class DestroyableProcessor extends CssMinProcessor implements Destroyable {
    public void destroy()
        throws Exception {
    }
  }

  @Test public void shouldBeDestroyableProcessorWhenDecoratingDestroyable() throws Exception{final DestroyableProcessor originalProcessor=Mockito.spy(new DestroyableProcessor());final ProcessorDecorator decorated=new ProcessorDecorator(originalProcessor);decorated.destroy();Mockito.verify(originalProcessor).destroy();}
}
