@Test public void sslErrorAddsOnlyFailedTlsModeToFailedRoute() throws Exception {
  Address address=new Address(uriHost,uriPort,socketFactory,hostnameVerifier,authenticator,Proxy.NO_PROXY,protocols);
  RouteDatabase routeDatabase=new RouteDatabase();
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,routeDatabase);
  dns.inetAddresses=makeFakeAddresses(255,1);
  Connection connection=routeSelector.next("GET");
  routeSelector.connectFailed(connection,new SSLHandshakeException("SSL exception"));
  assertTrue(routeDatabase.failedRoutesCount() == 1);
  assertTrue(routeSelector.hasNext());
}
