@Test public void testNotifyRouterUrls_Clean(){
  if (isScriptUnsupported)   return;
  RegistryDirectory registryDirectory=getRegistryDirectory();
  URL routerurl=URL.valueOf(Constants.ROUTE_PROTOCOL + "://127.0.0.1:9096/").addParameter(Constants.ROUTER_KEY,"javascript").addParameter(Constants.RULE_KEY,"function test1(){}").addParameter(Constants.ROUTER_KEY,"script");
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(routerurl);
  registryDirectory.notify(serviceUrls);
  List routers=registryDirectory.getRouters();
  Assert.assertEquals(1 + 1,routers.size());
  serviceUrls.clear();
  serviceUrls.add(routerurl.addParameter(Constants.ROUTER_KEY,Constants.ROUTER_TYPE_CLEAR));
  registryDirectory.notify(serviceUrls);
  routers=registryDirectory.getRouters();
  Assert.assertEquals(0 + 1,routers.size());
}
