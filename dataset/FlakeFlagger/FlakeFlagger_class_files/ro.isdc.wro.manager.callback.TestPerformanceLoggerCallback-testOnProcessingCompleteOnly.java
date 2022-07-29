/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.manager.callback;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alex Objelean
 */
public class TestPerformanceLoggerCallback {
  private PerformanceLoggerCallback callback;

  @Before
  public void setUp() {
    callback = new PerformanceLoggerCallback();
  }

  @Test public void testOnProcessingCompleteOnly(){callback.onBeforeModelCreated();callback.onProcessingComplete();}
}
