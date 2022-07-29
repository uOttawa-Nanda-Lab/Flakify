/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Arrays;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.cache.CacheKey;
import ro.isdc.wro.cache.CacheStrategy;
import ro.isdc.wro.cache.CacheValue;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.http.support.DelegatingServletOutputStream;
import ro.isdc.wro.http.support.HttpHeader;
import ro.isdc.wro.manager.callback.LifecycleCallback;
import ro.isdc.wro.manager.callback.PerformanceLoggerCallback;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.NoProcessorsWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.manager.runnable.ReloadModelRunnable;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.group.DefaultGroupExtractor;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.GroupExtractor;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.Destroyable;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.PlaceholderProcessor;
import ro.isdc.wro.model.resource.support.MutableResourceAuthorizationManager;
import ro.isdc.wro.model.resource.support.hash.CRC32HashStrategy;
import ro.isdc.wro.model.resource.support.hash.MD5HashStrategy;
import ro.isdc.wro.util.AbstractDecorator;
import ro.isdc.wro.util.ObjectFactory;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;
import ro.isdc.wro.util.io.UnclosableBufferedInputStream;


/**
 * TestWroManager.java.
 *
 * @author Alex Objelean
 * @created Created on Nov 3, 2008
 */
public class TestWroManager {
  private static final Logger LOG = LoggerFactory.getLogger(TestWroManager.class);
  @Mock
  private MutableResourceAuthorizationManager mockAuthorizationManager;
  @Mock
  private CacheStrategy<CacheKey, CacheValue> mockCacheStrategy;
  @Mock
  private WroModelFactory mockModelFactory;
  /**
   * Used to test simple operations.
   */
  private WroManager victim;
  /**
   * Used to test more complex use-cases.
   */
  private WroManagerFactory managerFactory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    final Context context = Context.webContext(Mockito.mock(HttpServletRequest.class), Mockito.mock(
        HttpServletResponse.class, Mockito.RETURNS_DEEP_STUBS), Mockito.mock(FilterConfig.class));

    Context.set(context, newConfigWithUpdatePeriodValue(0));

    managerFactory = new BaseWroManagerFactory().setModelFactory(getValidModelFactory())
        .setResourceAuthorizationManager(mockAuthorizationManager);

    final Injector injector = new InjectorBuilder(managerFactory).build();
    victim = managerFactory.create();
    injector.inject(victim);
  }

  /**
   * A processor which which uses a {@link WroManager} during processor, in order to process a single group, whose
   * resource is the pre processed resource of this processor.
   */
  private static final class WroManagerProcessor
      implements ResourcePreProcessor {

    private BaseWroManagerFactory createManagerFactory(final Resource resource) {
      return new BaseWroManagerFactory() {
        @Override
        protected void onAfterInitializeManager(final WroManager manager) {
          manager.registerCallback(new ObjectFactory<LifecycleCallback>() {
            public LifecycleCallback create() {
              return new PerformanceLoggerCallback();
            }
          });
        };

        @Override
        protected WroModelFactory newModelFactory() {
          return WroTestUtils.simpleModelFactory(new WroModel().addGroup(new Group("group").addResource(resource)));
        }
      };
    }

    public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
      LOG.debug("resource: {}", resource);

      final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
      final HttpServletResponse response = Context.get().getResponse();

      Mockito.when(response.getOutputStream()).thenReturn(
          new DelegatingServletOutputStream(new WriterOutputStream(writer)));
      Mockito.when(request.getRequestURI()).thenReturn("");

      final WroConfiguration config = new WroConfiguration();
      // we don't need caching here, otherwise we'll have clashing during unit tests.
      config.setDebug(true);
      config.setDisableCache(true);
      Context.set(Context.webContext(request, response, Mockito.mock(FilterConfig.class)), config);

      // create a groupExtractor which always return the same group name.
      final String groupName = "group";
      final GroupExtractor groupExtractor = new DefaultGroupExtractor() {
        @Override
        public String getGroupName(final HttpServletRequest request) {
          return groupName;
        }

        @Override
        public ResourceType getResourceType(final HttpServletRequest request) {
          return resource.getType();
        }
      };
      // this manager will make sure that we always process a model holding one group which has only one resource.
      final WroManagerFactory managerFactory = createManagerFactory(resource).setGroupExtractor(groupExtractor);
      managerFactory.create().process();
    }
  }

  private class GenericTestBuilder {
    /**
     * Perform a processing on a group extracted from requestUri and compares with the expectedResourceUri content.
     *
     * @param requestUri
     *          contains the group name to process.
     * @param expectedResourceUri
     *          the uri of the resource which has the expected content.
     */
    public void processAndCompare(final String requestUri, final String expectedResourceUri)
        throws Exception {
      final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
      final HttpServletResponse response = Context.get().getResponse();

      final ByteArrayOutputStream out = new ByteArrayOutputStream();
      Mockito.when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(out));
      Mockito.when(request.getRequestURI()).thenReturn(requestUri);

      final WroConfiguration config = new WroConfiguration();
      config.setIgnoreFailingProcessor(true);
      Context.set(Context.webContext(request, response, Mockito.mock(FilterConfig.class)), config);

      onBeforeProcess();

      managerFactory.create().process();

      // compare written bytes to output stream with the content from specified css.
      final InputStream expectedInputStream = new UnclosableBufferedInputStream(WroTestUtils
          .getInputStream(expectedResourceUri));
      final InputStream actualInputStream = new BufferedInputStream(new ByteArrayInputStream(out.toByteArray()));
      expectedInputStream.reset();
      WroTestUtils.compare(expectedInputStream, actualInputStream);
      expectedInputStream.close();
      actualInputStream.close();
    }

    /**
     * Allow to execute custom logic before the actual processing is done.
     */
    protected void onBeforeProcess() {
    }
  }

  /**
   * @return a {@link XmlModelFactory} pointing to a valid config resource.
   */
  private static XmlModelFactory getValidModelFactory() {
    return new XmlModelFactory() {
      @Override
      protected InputStream getModelResourceAsStream() {
        return TestWroManager.class.getResourceAsStream("wro.xml");
      }
    };
  }

  /**
   * Initialize {@link WroConfiguration} object with cacheUpdatePeriod & modelUpdatePeriod equal with provided argument.
   */
  private WroConfiguration newConfigWithUpdatePeriodValue(final long periodValue) {
    final WroConfiguration config = new WroConfiguration();
    config.setCacheUpdatePeriod(periodValue);
    config.setModelUpdatePeriod(periodValue);
    config.setDisableCache(true);
    return config;
  }

  private void genericIgnoreMissingResourceTest(final boolean ignoreFlag)
      throws Exception {
    new GenericTestBuilder() {
      @Override
      protected void onBeforeProcess() {
        final WroConfiguration config = Context.get().getConfig();
        config.setIgnoreFailingProcessor(ignoreFlag);
        config.setIgnoreMissingResources(ignoreFlag);
      };
    }.processAndCompare("/invalidImport.css", "classpath:ro/isdc/wro/manager/invalidImport-out.css");
  }

  @Test public void shouldRegisterCallback(){final LifecycleCallback mockCallback=Mockito.mock(LifecycleCallback.class);victim.registerCallback(new ObjectFactory<LifecycleCallback>(){public LifecycleCallback create(){return mockCallback;}});victim.getCallbackRegistry().onProcessingComplete();Mockito.verify(mockCallback,Mockito.atLeastOnce()).onProcessingComplete();}

  private static class DestroyableProcessor extends PlaceholderProcessor implements Destroyable {
    public void destroy() {
    }
  }
}
