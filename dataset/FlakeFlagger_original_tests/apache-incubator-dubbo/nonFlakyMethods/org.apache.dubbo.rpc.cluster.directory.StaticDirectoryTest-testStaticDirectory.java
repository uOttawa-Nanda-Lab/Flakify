@Test public void testStaticDirectory(){
  Router router=new ConditionRouterFactory().getRouter(getRouteUrl(" => " + " host = " + NetUtils.getLocalHost()));
  List<Router> routers=new ArrayList<Router>();
  routers.add(router);
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService"));
  Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  invokers.add(invoker1);
  invokers.add(invoker2);
  invokers.add(invoker3);
  List<Invoker<String>> filteredInvokers=router.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  StaticDirectory staticDirectory=new StaticDirectory(filteredInvokers);
  Boolean isAvailable=staticDirectory.isAvailable();
  Assert.assertTrue(!isAvailable);
  List<Invoker> newInvokers=staticDirectory.list(new MockDirInvocation());
  Assert.assertTrue(newInvokers.size() > 0);
  staticDirectory.destroy();
  Assert.assertTrue(newInvokers.size() == 0);
}
