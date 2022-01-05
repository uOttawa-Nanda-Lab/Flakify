@Test public void testRouteNotAvailable(){
  if (isScriptUnsupported)   return;
  URL url=initUrl("notAvailablerule.javascript");
  initInvocation("method1");
  initDic(url);
  initInvokers(url,true,false);
  MockClusterInvoker<FileRouterEngineTest> sinvoker=new MockClusterInvoker<FileRouterEngineTest>(dic,url);
  for (int i=0; i < 100; i++) {
    sinvoker.invoke(invocation);
    Invoker<FileRouterEngineTest> invoker=sinvoker.getSelectedInvoker();
    Assert.assertEquals(invoker2,invoker);
  }
}
