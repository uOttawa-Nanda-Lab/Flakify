package ro.isdc.wro.cache.support;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.cache.CacheKey;
import ro.isdc.wro.cache.CacheStrategy;
import ro.isdc.wro.cache.CacheValue;
import ro.isdc.wro.cache.impl.LruMemoryCacheStrategy;
import ro.isdc.wro.cache.impl.MemoryCacheStrategy;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.support.change.ResourceWatcher;
import ro.isdc.wro.util.ObjectDecorator;
import ro.isdc.wro.util.WroTestUtils;

/**
 * @author Alex Objelean
 */
public class TestDefaultSynchronizedCacheStrategyDecorator {
  private static final String GROUP_NAME = "g1";
  private static final String RESOURCE_URI = "/test.js";

  private CacheStrategy<CacheKey, CacheValue> victim;
  private ResourceWatcher mockResourceWatcher;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    victim = new DefaultSynchronizedCacheStrategyDecorator(new MemoryCacheStrategy<CacheKey, CacheValue>()) {
      @Override
      TimeUnit getTimeUnitForResourceWatcher() {
        //use milliseconds to make test faster
        return TimeUnit.MILLISECONDS;
      }

      @Override
      ResourceWatcher getResourceWatcher() {
        if (mockResourceWatcher == null) {
          mockResourceWatcher = Mockito.spy(super.getResourceWatcher());
        }
        return mockResourceWatcher;
      }
    };
    createInjector().inject(victim);
    //invoke getter explicitly to be sure we have a not null reference
    ((DefaultSynchronizedCacheStrategyDecorator) victim).getResourceWatcher();
  }

  @Test public void shouldNotCheckForChangesWhenResourceWatcherPeriodIsNotSet() throws Exception{final CacheKey key=new CacheKey("g1",ResourceType.JS,true);victim.get(key);victim.get(key);Mockito.verify(mockResourceWatcher,never()).check(key);}
}
