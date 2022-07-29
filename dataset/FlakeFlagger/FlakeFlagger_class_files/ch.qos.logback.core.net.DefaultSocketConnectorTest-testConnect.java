/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
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

import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.net.SocketConnector.ExceptionHandler;
import ch.qos.logback.core.net.server.ServerSocketUtil;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Unit tests for {@link DefaultSocketConnector}.
 *
 * @author Carl Harris
 */
public class DefaultSocketConnectorTest {

  private static final int DELAY = 1000;
  private static final int SHORT_DELAY = 10;
  private static final int RETRY_DELAY = 10;

  private MockExceptionHandler exceptionHandler = new MockExceptionHandler();
  
  private ServerSocket serverSocket;
  private DefaultSocketConnector connector;

  ExecutorService executor = Executors.newSingleThreadExecutor();

  @Before
  public void setUp() throws Exception {
    serverSocket = ServerSocketUtil.createServerSocket();
    connector = new DefaultSocketConnector(serverSocket.getInetAddress(),
        serverSocket.getLocalPort(), 0, RETRY_DELAY);
    connector.setExceptionHandler(exceptionHandler);
  }
  
  @Test public void testConnect() throws Exception{Future<Socket> connectorTask=executor.submit(connector);Socket socket=connectorTask.get(2 * DELAY,TimeUnit.MILLISECONDS);assertNotNull(socket);connectorTask.cancel(true);assertTrue(connectorTask.isDone());socket.close();}

  private static class MockExceptionHandler implements ExceptionHandler {

    private final Lock lock = new ReentrantLock();
    private final Condition failedCondition = lock.newCondition();
      
    private Exception lastException;
    
    public void connectionFailed(SocketConnector connector, Exception ex) {
      lastException = ex;
    }
    
    public Exception awaitConnectionFailed(long delay) 
         throws InterruptedException {
      lock.lock();
      try {
        long increment = 10;
        while (lastException == null && delay > 0) {
          boolean success = failedCondition.await(increment, TimeUnit.MILLISECONDS);
          delay -= increment;
          if(success) break;

        }
        return lastException;
      }
      finally {
        lock.unlock();
      }
    }
        
  }
  
}
