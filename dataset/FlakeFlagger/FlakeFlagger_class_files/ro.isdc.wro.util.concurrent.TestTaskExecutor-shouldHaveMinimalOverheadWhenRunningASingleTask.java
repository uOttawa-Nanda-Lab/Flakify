package ro.isdc.wro.util.concurrent;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.util.StopWatch;


/**
 * @author Alex Objelean
 */
public class TestTaskExecutor {
  private static final Logger LOG = LoggerFactory.getLogger(TestTaskExecutor.class);

  private TaskExecutor<Void> victim;

  @Before
  public void setUp() {
    victim = new TaskExecutor<Void>();
  }

  private Callable<Void> createSlowCallable(final long millis) {
    return new Callable<Void>() {
      public Void call()
          throws Exception {
        Thread.sleep(millis);
        return null;
      }
    };
  }

  @Test public void shouldHaveMinimalOverheadWhenRunningASingleTask() throws Exception{final Collection<Callable<Void>> callables=new ArrayList<Callable<Void>>();final long delay=100;callables.add(createSlowCallable(delay));final long start=System.currentTimeMillis();victim.submit(callables);final long end=System.currentTimeMillis();final long delta=end - start;LOG.debug("Execution took: {}",delta);final long overhead=40;assertTrue(delta < delay + overhead);}

  private static void printDate() {
    LOG.debug(new SimpleDateFormat("HH:ss:S").format(new Date()));
  }
}
