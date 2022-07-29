/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.manager.callback;

import java.io.StringWriter;
import java.util.concurrent.Callable;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.http.support.DelegatingServletOutputStream;
import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.GroupExtractor;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.util.ObjectFactory;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;

/**
 * @author Alex Objelean
 */
public class TestLifecycleCallbackRegistry {
  private LifecycleCallbackRegistry registry;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    registry = new LifecycleCallbackRegistry();
  }

  @Test public void shouldInvokeRegisteredCallbacks(){final LifecycleCallback callback=Mockito.mock(LifecycleCallback.class);registry.registerCallback(factoryFor(callback));registry.onBeforeModelCreated();Mockito.verify(callback).onBeforeModelCreated();registry.onAfterModelCreated();Mockito.verify(callback).onAfterModelCreated();registry.onBeforePreProcess();Mockito.verify(callback).onBeforePreProcess();registry.onAfterPreProcess();Mockito.verify(callback).onAfterPreProcess();registry.onBeforePostProcess();Mockito.verify(callback).onBeforePostProcess();registry.onAfterPostProcess();Mockito.verify(callback).onAfterPostProcess();registry.onBeforeMerge();Mockito.verify(callback).onBeforeMerge();registry.onAfterMerge();Mockito.verify(callback).onAfterMerge();registry.onProcessingComplete();Mockito.verify(callback).onProcessingComplete();}

  private ObjectFactory<LifecycleCallback> factoryFor(final LifecycleCallback callback) {
    return new ObjectFactory<LifecycleCallback>() {
      public LifecycleCallback create() {
        return callback;
      }
    };
  }
}
