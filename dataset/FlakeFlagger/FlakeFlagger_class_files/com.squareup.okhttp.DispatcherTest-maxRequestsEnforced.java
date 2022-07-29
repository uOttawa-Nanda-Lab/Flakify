package com.squareup.okhttp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class DispatcherTest {
  RecordingExecutor executor = new RecordingExecutor();
  RecordingReceiver receiver = new RecordingReceiver();
  Dispatcher dispatcher = new Dispatcher(executor);
  OkHttpClient client = new OkHttpClient().setDispatcher(dispatcher);

  @Before public void setUp() throws Exception {
    dispatcher.setMaxRequests(20);
    dispatcher.setMaxRequestsPerHost(10);
  }

  @Test public void maxRequestsEnforced() throws Exception{dispatcher.setMaxRequests(3);client.enqueue(newRequest("http://a/1"),receiver);client.enqueue(newRequest("http://a/2"),receiver);client.enqueue(newRequest("http://b/1"),receiver);client.enqueue(newRequest("http://b/2"),receiver);executor.assertJobs("http://a/1","http://a/2","http://b/1");}

  class RecordingExecutor extends AbstractExecutorService {
    private List<Job> jobs = new ArrayList<Job>();

    @Override public void execute(Runnable command) {
      jobs.add((Job) command);
    }

    public void assertJobs(String... expectedUrls) {
      List<String> actualUrls = new ArrayList<String>();
      for (Job job : jobs) {
        actualUrls.add(job.request().urlString());
      }
      assertEquals(Arrays.asList(expectedUrls), actualUrls);
    }

    public void finishJob(String url) {
      for (Iterator<Job> i = jobs.iterator(); i.hasNext(); ) {
        Job job = i.next();
        if (job.request().urlString().equals(url)) {
          i.remove();
          dispatcher.finished(job);
          return;
        }
      }
      throw new AssertionError("No such job: " + url);
    }

    @Override public void shutdown() {
      throw new UnsupportedOperationException();
    }

    @Override public List<Runnable> shutdownNow() {
      throw new UnsupportedOperationException();
    }

    @Override public boolean isShutdown() {
      throw new UnsupportedOperationException();
    }

    @Override public boolean isTerminated() {
      throw new UnsupportedOperationException();
    }

    @Override public boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException {
      throw new UnsupportedOperationException();
    }
  }

  private Request newRequest(String url) {
    return new Request.Builder().url(url).build();
  }

  private Request newRequest(String url, String tag) {
    return new Request.Builder().url(url).tag(tag).build();
  }
}
