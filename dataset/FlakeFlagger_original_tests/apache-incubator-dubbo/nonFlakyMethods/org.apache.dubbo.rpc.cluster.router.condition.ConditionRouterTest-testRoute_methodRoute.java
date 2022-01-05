@Test public void testRoute_methodRoute(){
  Invocation invocation=new RpcInvocation("getFoo",new Class<?>[0],new Object[0]);
  Router router=new ConditionRouterFactory().getRouter(getRouteUrl("methods=getFoo => host = 1.2.3.4"));
  boolean matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=setFoo,getFoo,findFoo"),invocation);
  Assert.assertEquals(true,matchWhen);
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=getFoo"),invocation);
  Assert.assertEquals(true,matchWhen);
  Router router2=new ConditionRouterFactory().getRouter(getRouteUrl("methods=getFoo & host!=1.1.1.1 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router2).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=getFoo"),invocation);
  Assert.assertEquals(false,matchWhen);
  Router router3=new ConditionRouterFactory().getRouter(getRouteUrl("methods=getFoo & host=1.1.1.1 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router3).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService?methods=getFoo"),invocation);
  Assert.assertEquals(true,matchWhen);
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  Invoker<String> invoker1=new MockInvoker<String>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService"));
  Invoker<String> invoker2=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  Invoker<String> invoker3=new MockInvoker<String>(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880/com.foo.BarService"));
  invokers.add(invoker1);
  invokers.add(invoker2);
  invokers.add(invoker3);
  Router router4=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " & methods = getFoo => "+ " host = 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  List<Invoker<String>> filteredInvokers1=router4.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),invocation);
  Assert.assertEquals(1,filteredInvokers1.size());
  Router router5=new ConditionRouterFactory().getRouter(getRouteUrl("host = " + NetUtils.getLocalHost() + " & methods = unvalidmethod => "+ " host = 10.20.3.3").addParameter(Constants.FORCE_KEY,String.valueOf(true)));
  List<Invoker<String>> filteredInvokers2=router5.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),invocation);
  Assert.assertEquals(3,filteredInvokers2.size());
}
