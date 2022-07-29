package ro.isdc.wro.model.resource.processor.decorator;

import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.SupportAware;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;


/**
 * @author Alex Objelean
 */
public class TestSupportAwareProcessorDecorator {
  @Mock
  private MockProcessor mockProcessor;
  @Mock
  private Resource mockResource;
  @Mock
  private Reader mockReader;
  @Mock
  private Writer mockWriter;

  private SupportAwareProcessorDecorator victim;

  private static interface MockProcessor
      extends ResourcePreProcessor, SupportAware {
  }

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);
    victim = new SupportAwareProcessorDecorator(mockProcessor);
  }

  @Test public void shouldCorrectlyDetectSupportOfDeepNestedProcessor(){victim=new SupportAwareProcessorDecorator(new ProcessorDecorator(new ProcessorDecorator(new JSMinProcessor())));assertTrue(victim.isSupported());}
}
