@Test public void testRoute_matchFilter(){
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService?default.serialization=fastjson"));
  Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  invokers.add(invoker1);
  invokers.add(invoker2);
  invokers.add(invoker3);
  Router router1=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host = 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  Router router2=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host = 10.20.3.* & host != 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  Router router3=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host = 10.20.3.3  & host != 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  Router router4=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host = 10.20.3.2,10.20.3.3,10.20.3.4").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  Router router5=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " host != 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  Router router6=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " => "+ " serialization = fastjson").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  List<Invoker<String>> filteredInvokers1=router1.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  List<Invoker<String>> filteredInvokers2=router2.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  List<Invoker<String>> filteredInvokers3=router3.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  List<Invoker<String>> filteredInvokers4=router4.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  List<Invoker<String>> filteredInvokers5=router5.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  List<Invoker<String>> filteredInvokers6=router6.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  Assert.assertEquals(1,filteredInvokers1.size());
  Assert.assertEquals(0,filteredInvokers2.size());
  Assert.assertEquals(0,filteredInvokers3.size());
  Assert.assertEquals(1,filteredInvokers4.size());
  Assert.assertEquals(2,filteredInvokers5.size());
  Assert.assertEquals(1,filteredInvokers6.size());
}
