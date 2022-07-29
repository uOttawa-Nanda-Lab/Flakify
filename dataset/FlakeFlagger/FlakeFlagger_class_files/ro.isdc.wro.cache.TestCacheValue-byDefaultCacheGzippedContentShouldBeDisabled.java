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

  @Test public void byDefaultCacheGzippedContentShouldBeDisabled(){Assert.assertFalse(Context.get().getConfig().isCacheGzippedContent());}
}
