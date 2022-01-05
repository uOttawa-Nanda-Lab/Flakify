@Test public void testRouteReturnAll(){
  Router router=new ScriptRouterFactory().getRouter(getRouteUrl("function route(op1,op2){return op1} route(invokers)"));
  List<Invoker<String>> invokers=new ArrayList<Invoker<String>>();
  invokers.add(new MockInvoker<String>());
  invokers.add(new MockInvoker<String>());
  invokers.add(new MockInvoker<String>());
  List<Invoker<String>> filteredInvokers=router.route(invokers,invokers.get(0).getUrl(),new RpcInvocation());
  Assert.assertEquals(invokers,filteredInvokers);
}
