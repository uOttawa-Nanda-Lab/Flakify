@Test public void explicitProxyTriesThatProxiesAddressesOnly() throws Exception {
  Address address=new Address(uriHost,uriPort,null,null,authenticator,proxyA,protocols);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,2);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[0],proxyAPort,false);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[1],proxyAPort,false);
  assertFalse(routeSelector.hasNext());
  dns.assertRequests(proxyAHost);
  proxySelector.assertRequests();
}
