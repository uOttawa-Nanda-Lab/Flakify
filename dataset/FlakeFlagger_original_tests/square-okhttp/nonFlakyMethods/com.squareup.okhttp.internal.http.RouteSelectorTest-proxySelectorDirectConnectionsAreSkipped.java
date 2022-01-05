@Test public void proxySelectorDirectConnectionsAreSkipped() throws Exception {
  Address address=new Address(uriHost,uriPort,null,null,authenticator,null,protocols);
  proxySelector.proxies.add(NO_PROXY);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  proxySelector.assertRequests(uri);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,1);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);
  dns.assertRequests(uriHost);
  assertFalse(routeSelector.hasNext());
}
