@Test public void proxySelectorReturnsMultipleProxies() throws Exception {
  Address address=new Address(uriHost,uriPort,null,null,authenticator,null,protocols);
  proxySelector.proxies.add(proxyA);
  proxySelector.proxies.add(proxyB);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  proxySelector.assertRequests(uri);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,2);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[0],proxyAPort,false);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[1],proxyAPort,false);
  dns.assertRequests(proxyAHost);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(254,1);
  assertConnection(routeSelector.next("GET"),address,proxyB,dns.inetAddresses[0],proxyBPort,false);
  dns.assertRequests(proxyBHost);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(253,1);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);
  dns.assertRequests(uriHost);
  assertFalse(routeSelector.hasNext());
}
