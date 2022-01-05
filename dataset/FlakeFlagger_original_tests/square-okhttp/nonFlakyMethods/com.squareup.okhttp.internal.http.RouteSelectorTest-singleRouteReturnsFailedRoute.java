@Test public void singleRouteReturnsFailedRoute() throws Exception {
  Address address=new Address(uriHost,uriPort,null,null,authenticator,null,protocols);
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,new RouteDatabase());
  assertTrue(routeSelector.hasNext());
  dns.inetAddresses=makeFakeAddresses(255,1);
  Connection connection=routeSelector.next("GET");
  RouteDatabase routeDatabase=new RouteDatabase();
  routeDatabase.failed(connection.getRoute());
  routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,routeDatabase);
  assertConnection(routeSelector.next("GET"),address,NO_PROXY,dns.inetAddresses[0],uriPort,false);
  assertFalse(routeSelector.hasNext());
  try {
    routeSelector.next("GET");
    fail();
  }
 catch (  NoSuchElementException expected) {
  }
}
