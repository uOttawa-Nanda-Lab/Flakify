/*
 * Copyright (C) 2011.
 * All rights reserved.
 */
package ro.isdc.wro.util;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Objelean
 */
public class TestSchedulerHelper {
  private static final Logger LOG = LoggerFactory.getLogger(TestSchedulerHelper.class);
  @Mock
  private Runnable mockRunnable;
  private SchedulerHelper helper;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test public void schedulerHelperIsSynchronized() throws Exception{helper=SchedulerHelper.create(new DestroyableLazyInitializer<Runnable>(){@Override protected Runnable initialize(){return new Runnable(){public void run(){try {LOG.debug("\tRunning thread ...");Thread.sleep(40);} catch (final Exception e){LOG.error("runnable interrupted",e);}}};}});final ThreadLocal<Long> period=new InheritableThreadLocal<Long>(){@Override protected Long initialValue(){return 0l;}};final ExecutorService service=Executors.newFixedThreadPool(5);for (int i=0;i < 10;i++){period.set(period.get() + 30);service.execute(new Runnable(){public void run(){helper.scheduleWithPeriod(period.get(),TimeUnit.MILLISECONDS);}});}Thread.sleep(400);helper.destroy();service.shutdown();}

  /**
   * creates a runnable which sleeps for a given period of time.
   *
   * @param period
   *          number of milliseconds to sleep.
   */
  private Runnable createSleepingRunnable(final long period) {
    return new Runnable() {
      public void run() {
        try {
          Thread.sleep(period);
        } catch (final Exception e) {
          LOG.error("thread interrupted", e);
        }
      }
    };
  }

  private void useNullRunnableWithPeriod(final long period) {
    createAndRunHelperForTest(null, period, TimeUnit.SECONDS);
  }


  private void createAndRunHelperForTest(final Runnable runnable, final long period, final TimeUnit timeUnit) {
    helper = SchedulerHelper.create(new DestroyableLazyInitializer<Runnable>() {
      @Override
      protected Runnable initialize() {
        return runnable;
      }
    });
    helper.scheduleWithPeriod(period, timeUnit);
  }
}
