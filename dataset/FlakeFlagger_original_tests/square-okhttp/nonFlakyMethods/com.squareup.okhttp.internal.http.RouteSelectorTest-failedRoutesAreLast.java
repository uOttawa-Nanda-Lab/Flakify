@Test public void failedRoutesAreLast() throws Exception {
  Address address=new Address(uriHost,uriPort,socketFactory,hostnameVerifier,authenticator,Proxy.NO_PROXY,protocols);
  RouteDatabase routeDatabase=new RouteDatabase();
  RouteSelector routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,routeDatabase);
  dns.inetAddresses=makeFakeAddresses(255,1);
  List<Connection> regularRoutes=new ArrayList<Connection>();
  while (routeSelector.hasNext()) {
    regularRoutes.add(routeSelector.next("GET"));
  }
  assertTrue(regularRoutes.size() > 1);
  routeDatabase.failed(regularRoutes.get(0).getRoute());
  routeSelector=new RouteSelector(address,uri,proxySelector,pool,dns,routeDatabase);
  List<Connection> routesWithFailedRoute=new ArrayList<Connection>();
  while (routeSelector.hasNext()) {
    routesWithFailedRoute.add(routeSelector.next("GET"));
  }
  assertEquals(regularRoutes.get(0).getRoute(),routesWithFailedRoute.get(routesWithFailedRoute.size() - 1).getRoute());
  assertEquals(regularRoutes.size(),routesWithFailedRoute.size());
}
