/** 
 * 1. notify twice, the second time notified router rules should completely replace the former one. 2. notify with no router url, do nothing to current routers 3. notify with only one router url, with router=clean, clear all current routers
 */
@Test public void testNotifyRouterUrls(){
  if (isScriptUnsupported)   return;
  RegistryDirectory registryDirectory=getRegistryDirectory();
  URL routerurl=URL.valueOf(Constants.ROUTE_PROTOCOL + "://127.0.0.1:9096/");
  URL routerurl2=URL.valueOf(Constants.ROUTE_PROTOCOL + "://127.0.0.1:9097/");
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(routerurl.addParameter(Constants.CATEGORY_KEY,Constants.ROUTERS_CATEGORY).addParameter(Constants.TYPE_KEY,"javascript").addParameter(Constants.ROUTER_KEY,"notsupported").addParameter(Constants.RULE_KEY,"function test1(){}"));
  serviceUrls.add(routerurl2.addParameter(Constants.CATEGORY_KEY,Constants.ROUTERS_CATEGORY).addParameter(Constants.TYPE_KEY,"javascript").addParameter(Constants.ROUTER_KEY,ScriptRouterFactory.NAME).addParameter(Constants.RULE_KEY,"function test1(){}"));
  registryDirectory.notify(serviceUrls);
  List<Router> routers=registryDirectory.getRouters();
  Assert.assertEquals(1 + 1,routers.size());
  Assert.assertTrue(ScriptRouter.class == routers.get(1).getClass() || ScriptRouter.class == routers.get(0).getClass());
  registryDirectory.notify(new ArrayList<URL>());
  routers=registryDirectory.getRouters();
  Assert.assertEquals(1 + 1,routers.size());
  Assert.assertTrue(ScriptRouter.class == routers.get(1).getClass() || ScriptRouter.class == routers.get(0).getClass());
  serviceUrls.clear();
  serviceUrls.add(routerurl.addParameter(Constants.ROUTER_KEY,Constants.ROUTER_TYPE_CLEAR));
  registryDirectory.notify(serviceUrls);
  routers=registryDirectory.getRouters();
  Assert.assertEquals(0 + 1,routers.size());
}
