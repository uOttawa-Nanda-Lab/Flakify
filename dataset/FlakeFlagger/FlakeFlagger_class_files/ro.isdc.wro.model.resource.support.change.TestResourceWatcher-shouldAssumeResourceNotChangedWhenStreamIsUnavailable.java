package ro.isdc.wro.model.resource.support.change;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ro.isdc.wro.cache.CacheKey;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.factory.AbstractUriLocatorFactory;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestResourceWatcher {
  /**
   * The uri to the first resource in a group.
   */
  private static final String RESOURCE_FIRST = "/path/1.js";
  private static final String RESOURCE_URI = "/test.css";
  private static final String GROUP_NAME = "g1";
  /**
   * Group containing two js resources.
   */
  private static final String GROUP_2 = "g2";
  private final CacheKey cacheEntry = new CacheKey(GROUP_NAME, ResourceType.CSS, true);
  private final CacheKey cacheEntry2 = new CacheKey(GROUP_2, ResourceType.JS, true);
  @Mock
  private UriLocator mockLocator;

  private ResourceWatcher victim;

  @Before
  public void setUp() {
    initMocks(this);
    Context.set(Context.standaloneContext());
    // spy the interface instead of WroTestUtils.createResourceMockingLocator() because of mockito bug which was
    // reported on their mailing list.
    mockLocator = Mockito.spy(new UriLocator() {
      public InputStream locate(final String uri)
          throws IOException {
        return new ByteArrayInputStream(uri.getBytes());
      }

      public boolean accept(final String uri) {
        return true;
      }
    });

    victim = new ResourceWatcher();
    createDefaultInjector().inject(victim);
  }

  @Test public void shouldAssumeResourceNotChangedWhenStreamIsUnavailable() throws Exception{victim=new ResourceWatcher(){@Override void onGroupChanged(final CacheKey cacheEntry){super.onGroupChanged(cacheEntry);Assert.fail("Should not detect the change");}};createDefaultInjector().inject(victim);final ResourceChangeDetector mockChangeDetector=Mockito.spy(victim.getResourceChangeDetector());Mockito.when(mockLocator.locate(Mockito.anyString())).thenThrow(new IOException("Resource is unavailable"));victim.check(cacheEntry);verify(mockChangeDetector,never()).checkChangeForGroup(Mockito.anyString(),Mockito.anyString());}

  private Answer<InputStream> answerWithContent(final String content) {
    return new Answer<InputStream>() {
      public InputStream answer(final InvocationOnMock invocation)
          throws Throwable {
        return new ByteArrayInputStream(content.getBytes());
      }
    };
  }
}
