/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.okhttp;

import com.squareup.okhttp.internal.RecordingHostnameVerifier;
import com.squareup.okhttp.internal.SslContextBuilder;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public final class ConnectionPoolTest {
  private static final int KEEP_ALIVE_DURATION_MS = 5000;
  private static final SSLContext sslContext = SslContextBuilder.localhost();

  private final MockWebServer spdyServer = new MockWebServer();
  private InetSocketAddress spdySocketAddress;
  private Address spdyAddress;

  private final MockWebServer httpServer = new MockWebServer();
  private Address httpAddress;
  private InetSocketAddress httpSocketAddress;

  private Connection httpA;
  private Connection httpB;
  private Connection httpC;
  private Connection httpD;
  private Connection httpE;
  private Connection spdyA;

  @Before public void setUp() throws Exception {
    spdyServer.useHttps(sslContext.getSocketFactory(), false);

    httpServer.play();
    httpAddress = new Address(httpServer.getHostName(), httpServer.getPort(), null, null,
        HttpAuthenticator.SYSTEM_DEFAULT, null, Protocol.SPDY3_AND_HTTP11);
    httpSocketAddress = new InetSocketAddress(InetAddress.getByName(httpServer.getHostName()),
        httpServer.getPort());

    spdyServer.play();
    spdyAddress = new Address(spdyServer.getHostName(), spdyServer.getPort(),
        sslContext.getSocketFactory(), new RecordingHostnameVerifier(),
        HttpAuthenticator.SYSTEM_DEFAULT, null,Protocol.SPDY3_AND_HTTP11);
    spdySocketAddress = new InetSocketAddress(InetAddress.getByName(spdyServer.getHostName()),
        spdyServer.getPort());

    Route httpRoute = new Route(httpAddress, Proxy.NO_PROXY, httpSocketAddress, true);
    Route spdyRoute = new Route(spdyAddress, Proxy.NO_PROXY, spdySocketAddress, true);
    httpA = new Connection(null, httpRoute);
    httpA.connect(200, 200, null);
    httpB = new Connection(null, httpRoute);
    httpB.connect(200, 200, null);
    httpC = new Connection(null, httpRoute);
    httpC.connect(200, 200, null);
    httpD = new Connection(null, httpRoute);
    httpD.connect(200, 200, null);
    httpE = new Connection(null, httpRoute);
    httpE.connect(200, 200, null);
    spdyA = new Connection(null, spdyRoute);
    spdyA.connect(20000, 20000, null);
  }

  @Test public void nonAliveConnectionNotReturned() throws Exception{ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);pool.recycle(httpA);httpA.close();assertNull(pool.get(httpAddress));assertPooled(pool);}

  private void assertPooled(ConnectionPool pool, Connection... connections) throws Exception {
    assertEquals(Arrays.asList(connections), pool.getConnections());
  }
}
