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

  private ObjectFactory<LifecycleCallback> factoryFor(final LifecycleCallback callback) {
    return new ObjectFactory<LifecycleCallback>() {
      public LifecycleCallback create() {
        return callback;
      }
    };
  }

  @Test
  public void shouldCatchCallbacksExceptionsAndContinueExecution() {
    final LifecycleCallback failingCallback = Mockito.mock(LifecycleCallback.class);
    final LifecycleCallback simpleCallback = Mockito.spy(new LifecycleCallbackSupport());

    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onBeforeModelCreated();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onAfterModelCreated();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onBeforePreProcess();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onAfterPreProcess();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onBeforePostProcess();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onAfterPostProcess();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onBeforeMerge();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onAfterMerge();
    Mockito.doThrow(new IllegalStateException()).when(failingCallback).onProcessingComplete();

    registry.registerCallback(factoryFor(failingCallback));
    registry.registerCallback(factoryFor(simpleCallback));

    registry.onBeforeModelCreated();
    registry.onAfterModelCreated();
    registry.onBeforePreProcess();
    registry.onAfterPreProcess();
    registry.onBeforePostProcess();
    registry.onAfterPostProcess();
    registry.onBeforeMerge();
    registry.onAfterMerge();
    registry.onProcessingComplete();

    Mockito.verify(simpleCallback).onBeforeModelCreated();
    Mockito.verify(simpleCallback).onAfterModelCreated();
    Mockito.verify(simpleCallback).onBeforePreProcess();
    Mockito.verify(simpleCallback).onAfterPreProcess();
    Mockito.verify(simpleCallback).onBeforePostProcess();
    Mockito.verify(simpleCallback).onAfterPostProcess();
    Mockito.verify(simpleCallback).onBeforeMerge();
    Mockito.verify(simpleCallback).onAfterMerge();
    Mockito.verify(simpleCallback).onProcessingComplete();
  }
}
