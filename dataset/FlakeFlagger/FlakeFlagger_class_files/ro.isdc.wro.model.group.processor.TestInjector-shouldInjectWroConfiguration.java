/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.group.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.ReadOnlyContext;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.config.support.ContextPropagatingCallable;
import ro.isdc.wro.manager.callback.LifecycleCallbackRegistry;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.group.Inject;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.CopyrightKeeperProcessorDecorator;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 * @created 12 Dec 2011
 */
public class TestInjector {
  private Injector victim;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    initializeValidInjector();
  }

  private void initializeValidInjector() {
    victim = InjectorBuilder.create(new BaseWroManagerFactory()).build();
  }

  @Test public void shouldInjectWroConfiguration() throws Exception{final Callable<?> inner=new Callable<Void>(){@Inject private WroConfiguration object;public Void call() throws Exception{Assert.assertNotNull(object);return null;}};victim.inject(inner);inner.call();}

  private class TestProcessor
      extends JSMinProcessor {
    @Inject
    private ReadOnlyContext context;
  }
}
