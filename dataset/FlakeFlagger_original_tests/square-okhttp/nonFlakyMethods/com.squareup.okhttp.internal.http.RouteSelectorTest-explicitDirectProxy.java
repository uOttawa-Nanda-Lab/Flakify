@Test public void explicitDirectProxy() throws Exception {
  Address address=new Address(uriHost,uriPort,null,null,authenticator,NO_PROXY,protocols);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,2);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[1],uriPort,false);
  assertFalse(routeSelector.hasNext());
  dns.assertRequests(uri.getHost());
  proxySelector.assertRequests();
}
