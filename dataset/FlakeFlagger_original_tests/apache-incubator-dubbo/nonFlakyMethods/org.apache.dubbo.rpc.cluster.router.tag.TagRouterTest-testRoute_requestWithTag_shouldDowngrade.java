@Test public void testRoute_requestWithTag_shouldDowngrade(){
  RpcContext.getContext().setAttachment(Constants.REQUEST_TAG_KEY,"black");
  List<Invoker<String>> invokers=new ArrayList<>();
  Invoker<String> redInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.1:20880/com.foo.BarService?tag=red"));
  Invoker<String> yellowInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.2:20880/com.foo.BarService?tag=yellow"));
  Invoker<String> blueInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.3:20880/com.foo.BarService?tag=blue"));
  Invoker<String> defaultInvoker=new MockInvoker<>(URL.valueOf("dubbo://10.20.3.4:20880/com.foo.BarService"));
  invokers.add(redInvoker);
  invokers.add(yellowInvoker);
  invokers.add(blueInvoker);
  invokers.add(defaultInvoker);
  Router tagRouter=new TagRouterFactory().getRouter(tagUrl);
  List<Invoker<String>> filteredInvokers=tagRouter.route(invokers,URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/com.foo.BarService"),new RpcInvocation());
  Assert.assertTrue(filteredInvokers.contains(defaultInvoker));
  Assert.assertFalse(filteredInvokers.contains(yellowInvoker));
  Assert.assertFalse(filteredInvokers.contains(blueInvoker));
  Assert.assertFalse(filteredInvokers.contains(redInvoker));
}
