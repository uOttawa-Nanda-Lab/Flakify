@Test public void testRoutePickInvokers(){
  String rule="var result = new java.util.ArrayList(invokers.size());" + "for (i=0;i<invokers.size(); i++){ " + "if (invokers.get(i).isAvailable()) {"+ "result.add(invokers.get(i)) ;"+ "}"+ "} ; "+ "return result;";
  String script="function route(invokers,invocation,context){" + rule + "} route(invokers,invocation,context)";
  Router router=new ScriptRouterFactory().getRouter(getRouteUrl(script));
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  Invoker<String> invoker1=new MockInvoker<String>(false);
  Invoker<String> invoker2=new MockInvoker<String>(true);
  Invoker<String> invoker3=new MockInvoker<String>(true);
  invokers.add(invoker1);
  invokers.add(invoker2);
  invokers.add(invoker3);
  List<Invoker<String>> filteredInvokers=router.route(invokers,invokers.get(0).getUrl(),new RpcInvocation());
  Assert.assertEquals(2,filteredInvokers.size());
  Assert.assertEquals(invoker2,filteredInvokers.get(0));
  Assert.assertEquals(invoker3,filteredInvokers.get(1));
}
