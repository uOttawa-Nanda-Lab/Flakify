@Test public void testRoute_requestWithoutTag_shouldNotDowngrade(){
  RpcContext.getContext().setAttachment(Constants.REQUEST_TAG_KEY,"");
  List<Invoker<String>> invokers=new ArrayList<>();
  Invoker<String> redInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.1:20880/com.foo.BarService?tag=red"));
  Invoker<String> yellowInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.2:20880/com.foo.BarService?tag=yellow"));
  Invoker<String> blueInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService?tag=blue"));
  invokers.add(redInvoker);
  invokers.add(yellowInvoker);
  invokers.add(blueInvoker);
  Router tagRouter=new TagRouterFactory().getRouter(tagUrl);
  List<Invoker<String>> filteredInvokers=tagRouter.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  Assert.assertEquals(0,filteredInvokers.size());
}
