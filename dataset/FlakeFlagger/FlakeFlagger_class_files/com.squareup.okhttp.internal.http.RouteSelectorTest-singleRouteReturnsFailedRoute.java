/*
 * Copyright (C) 2012 Square, Inc.
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
package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Address;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkAuthenticator;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.RouteDatabase;
import com.squareup.okhttp.internal.Dns;
import com.squareup.okhttp.internal.SslContextBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;
import org.junit.Test;

import static java.net.Proxy.NO_PROXY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public final class RouteSelectorTest {
  private static final int proxyAPort = 1001;
  private static final String proxyAHost = "proxyA";
  private static final Proxy proxyA =
      new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAHost, proxyAPort));
  private static final int proxyBPort = 1002;
  private static final String proxyBHost = "proxyB";
  private static final Proxy proxyB =
      new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyBHost, proxyBPort));
  private static final URI uri;
  private static final String uriHost = "hostA";
  private static final int uriPort = 80;

  private static final SSLContext sslContext = SslContextBuilder.localhost();
  private static final SSLSocketFactory socketFactory = sslContext.getSocketFactory();
  private static final HostnameVerifier hostnameVerifier;
  private static final ConnectionPool pool;

  static {
    try {
      uri = new URI("http://" + uriHost + ":" + uriPort + "/path");
      pool = ConnectionPool.getDefault();
      hostnameVerifier = HttpsURLConnectionImpl.getDefaultHostnameVerifier();
    } catch (Exception e) {
      throw new AssertionError(e);
    }
  }

  private final OkAuthenticator authenticator = HttpAuthenticator.SYSTEM_DEFAULT;
  private final List<Protocol> protocols = Arrays.asList(Protocol.HTTP_11);
  private final FakeDns dns = new FakeDns();
  private final FakeProxySelector proxySelector = new FakeProxySelector();

  @Test public void singleRouteReturnsFailedRoute() throws Exception{Address address=new Address(uriHost,uriPort,null,null,authenticator,null,protocols);RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());assertTrue(routeSelector.hasNext());dns.inetAddresses=makeFakeAddresses(255,1);Connection connection=routeSelector.next("GET");RouteDatabase routeDatabase=new RouteDatabase();routeDatabase.failed(connection.getRoute());routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,routeDatabase);assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);assertFalse(routeSelector.hasNext());try {routeSelector.next("GET");fail();} catch (NoSuchElementException expected){}}

  private void assertConnection(Connection connection, Address address, Proxy proxy,
      InetAddress socketAddress, int socketPort, boolean modernTls) {
    assertEquals(address, connection.getRoute().getAddress());
    assertEquals(proxy, connection.getRoute().getProxy());
    assertEquals(socketAddress, connection.getRoute().getSocketAddress().getAddress());
    assertEquals(socketPort, connection.getRoute().getSocketAddress().getPort());
    assertEquals(modernTls, connection.getRoute().isModernTls());
  }

  private static InetAddress[] makeFakeAddresses(int prefix, int count) {
    try {
      InetAddress[] result = new InetAddress[count];
      for (int i = 0; i < count; i++) {
        result[i] =
            InetAddress.getByAddress(new byte[] { (byte) prefix, (byte) 0, (byte) 0, (byte) i });
      }
      return result;
    } catch (UnknownHostException e) {
      throw new AssertionError();
    }
  }

  private static class FakeDns implements Dns {
    List<String> requestedHosts = new ArrayList<String>();
    InetAddress[] inetAddresses;

    @Override public InetAddress[] getAllByName(String host) throws UnknownHostException {
      requestedHosts.add(host);
      if (inetAddresses == null) throw new UnknownHostException();
      return inetAddresses;
    }

    public void assertRequests(String... expectedHosts) {
      assertEquals(Arrays.asList(expectedHosts), requestedHosts);
      requestedHosts.clear();
    }
  }

  private static class FakeProxySelector extends ProxySelector {
    List<URI> requestedUris = new ArrayList<URI>();
    List<Proxy> proxies = new ArrayList<Proxy>();
    List<String> failures = new ArrayList<String>();

    @Override public List<Proxy> select(URI uri) {
      requestedUris.add(uri);
      return proxies;
    }

    public void assertRequests(URI... expectedUris) {
      assertEquals(Arrays.asList(expectedUris), requestedUris);
      requestedUris.clear();
    }

    @Override public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
      InetSocketAddress socketAddress = (InetSocketAddress) sa;
      failures.add(
          String.format("%s %s:%d %s", uri, socketAddress.getHostName(), socketAddress.getPort(),
              ioe.getMessage()));
    }
  }
}
