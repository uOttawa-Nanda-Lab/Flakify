@Test public void testRoute_Force(){
  Router router=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host = 1.2.3.4").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService"));
  Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  invokers.add(invoker1);
  invokers.add(invoker2);
  invokers.add(invoker3);
  List<Invoker<String>> filteredInvokers=router.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  Assert.assertEquals(0,filteredInvokers.size());
}
