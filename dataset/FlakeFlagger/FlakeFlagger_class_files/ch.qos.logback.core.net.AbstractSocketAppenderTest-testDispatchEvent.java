/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2011, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */

package ch.qos.logback.core.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;

import ch.qos.logback.core.BasicStatusManager;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.qos.logback.core.net.mock.MockContext;
import ch.qos.logback.core.net.server.ServerSocketUtil;
import ch.qos.logback.core.spi.PreSerializationTransformer;

/**
 * Unit tests for {@link AbstractSocketAppender}.
 *
 * @author Carl Harris
 */
public class AbstractSocketAppenderTest {

  private static final int DELAY = 10000;

  private ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
  private MockContext mockContext = new MockContext(executorService);
  private InstrumentedSocketAppender instrumentedAppender = new InstrumentedSocketAppender();

  @Before
  public void setUp() throws Exception {
    instrumentedAppender.setContext(mockContext);
  }

  private void waitForActiveCountToEqual(ThreadPoolExecutor executorService, int i) {
    while (executorService.getActiveCount() != i) {
      try {
        Thread.yield();
        Thread.sleep(1);
        System.out.print(".");
      } catch (InterruptedException e) {
      }
    }
  }


  @Test public void testDispatchEvent() throws Exception{ServerSocket serverSocket=ServerSocketUtil.createServerSocket();instrumentedAppender.setRemoteHost(serverSocket.getInetAddress().getHostAddress());instrumentedAppender.setPort(serverSocket.getLocalPort());instrumentedAppender.setQueueSize(1);instrumentedAppender.start();Socket appenderSocket=serverSocket.accept();serverSocket.close();instrumentedAppender.append("some event");final int shortDelay=100;for (int i=0,retries=DELAY / shortDelay;!instrumentedAppender.lastQueue.isEmpty() && i < retries;i++){Thread.sleep(shortDelay);}assertTrue(instrumentedAppender.lastQueue.isEmpty());ObjectInputStream ois=new ObjectInputStream(appenderSocket.getInputStream());assertEquals("some event",ois.readObject());appenderSocket.close();}

  private static class InstrumentedSocketAppender extends AbstractSocketAppender<String> {

    private BlockingQueue<String> lastQueue;

    @Override
    protected void postProcessEvent(String event) {
    }

    @Override
    protected PreSerializationTransformer<String> getPST() {
      return new PreSerializationTransformer<String>() {
        public Serializable transform(String event) {
          return event;
        }
      };
    }

    @Override
    BlockingQueue<String> newBlockingQueue(int queueSize) {
      lastQueue = super.newBlockingQueue(queueSize);
      return lastQueue;
    }

  }

}
