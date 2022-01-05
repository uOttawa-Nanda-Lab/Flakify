@Test public void nonSslErrorAddsAllTlsModesToFailedRoute() throws Exception {
  Address address=new Address(uriHost,uriPort,socketFactory,hostnameVerifier,authenticator,Proxy.NO_PROXY,protocols);
  RouteDatabase routeDatabase=new RouteDatabase();
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,routeDatabase);
  dns.inetAddresses=makeFakeAddresses(255,1);
  Connection connection=routeSelector.next("GET");
  routeSelector.connectFailed(connection,new IOException("Non SSL exception"));
  assertTrue(routeDatabase.failedRoutesCount() == 2);
  assertFalse(routeSelector.hasNext());
}
