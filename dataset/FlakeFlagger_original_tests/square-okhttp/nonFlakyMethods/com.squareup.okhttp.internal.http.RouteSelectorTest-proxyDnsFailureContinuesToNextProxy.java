@Test public void proxyDnsFailureContinuesToNextProxy() throws Exception {
  Address address=new Address(uriHost,uriPort,null,null,authenticator,null,protocols);
  proxySelector.proxies.add(proxyA);
  proxySelector.proxies.add(proxyB);
  proxySelector.proxies.add(proxyA);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  proxySelector.assertRequests(uri);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,1);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[0],proxyAPort,false);
  dns.assertRequests(proxyAHost);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=null;
  try {
    routeSelector.next("GET");
    fail();
  }
 catch (  UnknownHostException expected) {
  }
  dns.assertRequests(proxyBHost);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,1);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[0],proxyAPort,false);
  dns.assertRequests(proxyAHost);
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(254,1);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);
  dns.assertRequests(uriHost);
  assertFalse(routeSelector.hasNext());
}
