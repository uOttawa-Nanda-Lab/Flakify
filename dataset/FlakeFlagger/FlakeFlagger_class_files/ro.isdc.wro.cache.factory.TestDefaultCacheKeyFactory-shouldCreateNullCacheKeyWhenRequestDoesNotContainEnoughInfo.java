package ro.isdc.wro.cache.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ro.isdc.wro.cache.CacheKey;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.group.GroupExtractor;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.ResourceType;


/**
 * @author Alex Objelean
 */
public class TestDefaultCacheKeyFactory {
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private GroupExtractor mockGroupExtractor;
  private DefaultCacheKeyFactory victim;

  @Before
  public void setUp() {
    initMocks(this);
    Context.set(Context.standaloneContext());
    victim = new DefaultCacheKeyFactory();
    final WroManagerFactory managerFactory = new BaseWroManagerFactory().setGroupExtractor(mockGroupExtractor);
    InjectorBuilder.create(managerFactory).build().inject(victim);
  }

  @Test public void shouldCreateNullCacheKeyWhenRequestDoesNotContainEnoughInfo(){assertNull(victim.create(mockRequest));}
}
