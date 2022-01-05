@Test public void multipleProxiesMultipleInetAddressesMultipleTlsModes() throws Exception {
  Address address=new Address(uriHost,uriPort,socketFactory,hostnameVerifier,authenticator,null,protocols);
  proxySelector.proxies.add(proxyA);
  proxySelector.proxies.add(proxyB);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  dns.inetAddresses=makeFakeAddresses(255,2);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[0],proxyAPort,true);
  dns.assertRequests(proxyAHost);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[0],proxyAPort,false);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[1],proxyAPort,true);
  assertConnection(routeSelector.next("GET"),address,proxyA,dns.inetAddresses[1],proxyAPort,false);
  dns.inetAddresses=makeFakeAddresses(254,2);
  assertConnection(routeSelector.next("GET"),address,proxyB,dns.inetAddresses[0],proxyBPort,true);
  dns.assertRequests(proxyBHost);
  assertConnection(routeSelector.next("GET"),address,proxyB,dns.inetAddresses[0],proxyBPort,false);
  assertConnection(routeSelector.next("GET"),address,proxyB,dns.inetAddresses[1],proxyBPort,true);
  assertConnection(routeSelector.next("GET"),address,proxyB,dns.inetAddresses[1],proxyBPort,false);
  dns.inetAddresses=makeFakeAddresses(253,2);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,true);
  dns.assertRequests(uriHost);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[1],uriPort,true);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[1],uriPort,false);
  assertFalse(routeSelector.hasNext());
}
