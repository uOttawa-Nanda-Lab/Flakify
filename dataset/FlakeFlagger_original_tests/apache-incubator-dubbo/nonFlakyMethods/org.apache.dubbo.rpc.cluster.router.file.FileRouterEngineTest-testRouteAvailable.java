@Test public void testRouteAvailable(){
  if (isScriptUnsupported)   return;
  URL url=initUrl("availablerule.javascript");
  initInvocation("method1");
  initDic(url);
  initInvokers(url);
  MockClusterInvoker<FileRouterEngineTest> sinvoker=new MockClusterInvoker<FileRouterEngineTest>(dic,url);
  for (int i=0; i < 100; i++) {
    sinvoker.invoke(invocation);
    Invoker<FileRouterEngineTest> invoker=sinvoker.getSelectedInvoker();
    Assert.assertEquals(invoker1,invoker);
  }
}
