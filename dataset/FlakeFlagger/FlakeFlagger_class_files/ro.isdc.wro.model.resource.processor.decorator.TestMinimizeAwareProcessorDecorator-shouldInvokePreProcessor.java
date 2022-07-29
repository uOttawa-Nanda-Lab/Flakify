package ro.isdc.wro.model.resource.processor.decorator;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.MinimizeAware;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;


/**
 * @author Alex Objelean
 */
public class TestMinimizeAwareProcessorDecorator {
  @Mock
  private ResourcePreProcessor mockPreProcessor;
  @Mock
  private ResourcePostProcessor mockPostProcessor;
  private Reader mockReader;
  private Writer mockWriter;
  private MinimizeAwareProcessorDecorator victim;
  /**
   * A processor which performs some sort of minimization.
   */
  private static class MinimizeAwareProcessor
      implements ResourcePreProcessor, ResourcePostProcessor, MinimizeAware {
    public void process(final Reader reader, final Writer writer)
        throws IOException {
    }
    public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
    }
    public boolean isMinimize() {
      return true;
    }
  }

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);
    mockReader = new StringReader("");
    mockWriter = new StringWriter();
  }

  private void initVictim() {
    InjectorBuilder.create(new BaseWroManagerFactory()).build().inject(victim);
  }

  @Test public void shouldInvokePreProcessor() throws Exception{victim=new MinimizeAwareProcessorDecorator(mockPreProcessor);initVictim();victim.process(null,mockReader,mockWriter);Mockito.verify(mockPreProcessor,Mockito.atLeastOnce()).process(Mockito.any(Resource.class),Mockito.any(Reader.class),Mockito.any(Writer.class));}
}
