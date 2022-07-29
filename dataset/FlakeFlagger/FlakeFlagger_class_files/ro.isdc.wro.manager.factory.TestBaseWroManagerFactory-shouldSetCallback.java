/**
 * Copyright@2011 wro4j
 */
package ro.isdc.wro.manager.factory;

import static org.mockito.Mockito.verify;

import java.util.concurrent.Callable;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.cache.CacheKey;
import ro.isdc.wro.cache.CacheStrategy;
import ro.isdc.wro.cache.CacheValue;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.support.ContextPropagatingCallable;
import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.callback.LifecycleCallback;
import ro.isdc.wro.manager.callback.LifecycleCallbackSupport;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.support.naming.NoOpNamingStrategy;
import ro.isdc.wro.util.ObjectFactory;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;


/**
 * @author Alex Objelean
 */
public class TestBaseWroManagerFactory {
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletResponse mockResponse;
  @Mock
  private FilterConfig mockFilterConfig;
  @Mock
  private WroModelFactory mockModelFactory;
  @Mock
  private CacheStrategy<CacheKey, CacheValue> mockCacheStrategy;
  private BaseWroManagerFactory victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Context.set(Context.webContext(mockRequest, mockResponse, mockFilterConfig));
    victim = new BaseWroManagerFactory();
  }

  @Test public void shouldSetCallback() throws Exception{final LifecycleCallback callback=Mockito.spy(new LifecycleCallbackSupport());victim=new BaseWroManagerFactory().setModelFactory(WroUtil.factoryFor(new WroModel()));final WroManager manager=victim.create();InjectorBuilder.create(victim).build().inject(manager);manager.registerCallback(new ObjectFactory<LifecycleCallback>(){public LifecycleCallback create(){return callback;}});manager.getModelFactory().create();Mockito.verify(callback).onBeforeModelCreated();Mockito.verify(callback).onAfterModelCreated();}
}
