/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.model.group.processor;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.factory.SimpleUriLocatorFactory;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.CopyrightKeeperProcessorDecorator;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.util.StopWatch;
import ro.isdc.wro.util.WroUtil;


/**
 * @author Alex Objelean
 */
public class TestPreProcessorExecutor {
  private static final Logger LOG = LoggerFactory.getLogger(TestPreProcessorExecutor.class);
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletResponse mockResponse;
  @Mock
  private FilterConfig mockFilterConfig;
  @Mock
  private ServletContext mockServletContext;
  @Mock
  private UriLocatorFactory mockLocatorFactory;
  @Mock
  private UriLocator mockLocator;
  private PreProcessorExecutor victim;


  @Before
  public void setUp() throws Exception {
    initMocks(this);

    when(mockRequest.getRequestURL()).thenReturn(new StringBuffer(""));
    when(mockRequest.getServletPath()).thenReturn("");
    when(mockFilterConfig.getServletContext()).thenReturn(mockServletContext);
    when(mockLocatorFactory.locate(Mockito.anyString())).thenReturn(WroUtil.EMPTY_STREAM);
    when(mockLocator.locate(Mockito.anyString())).thenReturn(WroUtil.EMPTY_STREAM);
    when(mockLocatorFactory.getInstance(Mockito.anyString())).thenReturn(mockLocator);

    final Context context = Context.webContext(mockRequest, mockResponse, mockFilterConfig);
    Context.set(context);
    //force parallel execution
    Context.get().getConfig().setParallelPreprocessing(true);
    Context.get().getConfig().setIgnoreFailingProcessor(true);
    initExecutor();
  }


  private WroManagerFactory createWroManager(final ResourcePreProcessor... preProcessors) {
    final SimpleProcessorsFactory processorsFactory = new SimpleProcessorsFactory();
    for (final ResourcePreProcessor resourcePreProcessor : preProcessors) {
      processorsFactory.addPreProcessor(resourcePreProcessor);
    }
    final BaseWroManagerFactory wroManagerFactory = new BaseWroManagerFactory();
    wroManagerFactory.setProcessorsFactory(processorsFactory);
    wroManagerFactory.setUriLocatorFactory(mockLocatorFactory);
    return wroManagerFactory;
  }


  /**
   * @param wroManagerFactory
   */
  private void initExecutor(final ResourcePreProcessor... preProcessors) {
    final WroManagerFactory wroManagerFactory = createWroManager(preProcessors);
    final Injector injector = InjectorBuilder.create(wroManagerFactory).build();
    victim = new PreProcessorExecutor();
    injector.inject(victim);
  }


  /**
   * Creates a slow pre processor which sleeps for a given amount of milliseconds and doesn't change the processed
   * content.
   */
  private ResourcePreProcessor createSlowPreProcessor(final long time) {
    return new ResourcePreProcessor() {
      public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
        try {
          IOUtils.copy(reader, writer);
          Thread.sleep(time);
        } catch (final InterruptedException e) {
        }
      }
    };
  }


  private ResourcePreProcessor createProcessorUsingMissingResource() {
    return new ResourcePreProcessor() {
      public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
        LOG.debug("executing processor which will throw IOException");
        throw new IOException("Invalid resource found!");
      }
    };
  }


  private ResourcePreProcessor createProcessorWhichFails() {
    return new ResourcePreProcessor() {
      public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
        LOG.debug("executing failing processor...");
        throw new WroRuntimeException("Boom!");
      }
    };
  }


  private List<Resource> createResources(final Resource... resources) {
    final List<Resource> resourcesList = new ArrayList<Resource>();
    for (final Resource resource : resources) {
      resourcesList.add(resource);
    }
    return resourcesList;
  }


  @Test(expected=IOException.class) public void shouldFailWhenProcessingInvalidResource() throws Exception{when(mockLocatorFactory.locate(Mockito.anyString())).thenThrow(IOException.class);Context.get().getConfig().setIgnoreMissingResources(false);shouldNotFailWhenProcessingInvalidResource();}

  private void useFailingPreProcessor() throws Exception {
    initExecutor(createProcessorWhichFails());
    final List<Resource> resources = createResources(Resource.create("", ResourceType.JS));
    final String result = victim.processAndMerge(resources, true);
    Assert.assertEquals("", result);

  }

  private static class AnyTypeProcessor
      implements ResourcePreProcessor, ResourcePostProcessor {
    public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
    }

    public void process(final Reader reader, final Writer writer)
        throws IOException {
    }
  }

  @Minimize
  private static class MinimizeAwareProcessor
      extends AnyTypeProcessor {
  }
}
