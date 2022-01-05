@Test public void testRoute_createBySpi(){
  URL zkProvider=URL.valueOf("zookeeper://10.20.3.1:20880/com.foo.BarService?router=tag");
  String parameter=zkProvider.getParameter(Constants.ROUTER_KEY);
  RouterFactory routerFactory=ExtensionLoader.getExtensionLoader(RouterFactory.class).getExtension(parameter);
  Router tagRouter=routerFactory.getRouter(zkProvider);
  Assert.assertTrue(tagRouter instanceof TagRouter);
}
