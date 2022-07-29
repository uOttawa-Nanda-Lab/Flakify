/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.cache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.WroConfiguration;

/**
 * @author Alex Objelean
 */
public class TestCacheValue {
  private static final String RAW_CONTENT = "[RAW_CONTENT]";
  @Before
  public void setUp() {
    final Context ctx = Context.standaloneContext();
    Context.set(ctx);
  }

  @Test public void changingFlagShouldHaveNoEffect(){final WroConfiguration config=Context.get().getConfig();config.setCacheGzippedContent(false);final CacheValue entry=CacheValue.valueOf(RAW_CONTENT,"hash");Assert.assertNull(null,entry.getGzippedContentInternal());config.setCacheGzippedContent(true);Assert.assertNull(null,entry.getGzippedContentInternal());Assert.assertNotNull(entry.getGzippedContent());}
}
