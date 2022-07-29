package ro.isdc.wro.extensions.processor.css;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.group.processor.PreProcessorExecutor;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.LazyProcessorDecorator;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;
import ro.isdc.wro.model.resource.processor.support.ProcessingCriteria;
import ro.isdc.wro.model.resource.processor.support.ProcessingType;
import ro.isdc.wro.util.LazyInitializer;
import ro.isdc.wro.util.WroTestUtils;

/**
 * @author Alex Objelean
 */
public class TestLessCssProcessor {
  @Mock
  private Resource mockResource;
  @Mock
  private Reader mockReader;
  @Mock
  private Writer mockWriter;
  @Mock
  private NodeLessCssProcessor mockNodeProcessor;
  @Mock
  private ResourcePreProcessor mockRhinoProcessor;
  private ResourcePreProcessor victim;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);
    //use lazy initialization to defer constructor invocation
    victim = new LazyProcessorDecorator(new LazyInitializer<ResourcePreProcessor>() {
      @Override
      protected ResourcePreProcessor initialize() {
        return new LessCssProcessor() {
          @Override
          protected ResourcePreProcessor createFallbackProcessor() {
            return mockRhinoProcessor;
          }

          @Override
          protected NodeLessCssProcessor createNodeProcessor() {
            return mockNodeProcessor;
          }
        };
      }
    });
    WroTestUtils.createInjector().inject(victim);
  }

  @Test public void shouldUseFallbackProcessorWhenNodeNotSupported() throws Exception{when(mockNodeProcessor.isSupported()).thenReturn(false);victim.process(mockResource,mockReader,mockWriter);verify(mockNodeProcessor,Mockito.never()).process(mockResource,mockReader,mockWriter);verify(mockRhinoProcessor,Mockito.times(1)).process(mockResource,mockReader,mockWriter);}
}
