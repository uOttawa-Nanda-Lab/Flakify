@Test public void testRoute_Placeholder(){
  Router router=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host = $host"));
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService"));
  Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  invokers.add(invoker1);
  invokers.add(invoker2);
  invokers.add(invoker3);
  List<Invoker<String>> filteredInvokers=router.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  Assert.assertEquals(2,filteredInvokers.size());
  Assert.assertEquals(invoker2,filteredInvokers.get(0));
  Assert.assertEquals(invoker3,filteredInvokers.get(1));
}
