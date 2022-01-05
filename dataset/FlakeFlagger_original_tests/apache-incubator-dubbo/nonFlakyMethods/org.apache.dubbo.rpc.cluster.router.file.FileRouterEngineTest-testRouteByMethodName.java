@Test public void testRouteByMethodName(){
  if (isScriptUnsupported)   return;
  URL url=initUrl("methodrule.javascript");
{
    initInvocation("method1");
    initDic(url);
    initInvokers(url,true,true);
    MockClusterInvoker<FileRouterEngineTest> sinvoker=new MockClusterInvoker<FileRouterEngineTest>(dic,url);
    for (int i=0; i < 100; i++) {
      sinvoker.invoke(invocation);
      Invoker<FileRouterEngineTest> invoker=sinvoker.getSelectedInvoker();
      Assert.assertEquals(invoker1,invoker);
    }
  }
{
    initInvocation("method2");
    initDic(url);
    initInvokers(url,true,true);
    MockClusterInvoker<FileRouterEngineTest> sinvoker=new MockClusterInvoker<FileRouterEngineTest>(dic,url);
    for (int i=0; i < 100; i++) {
      sinvoker.invoke(invocation);
      Invoker<FileRouterEngineTest> invoker=sinvoker.getSelectedInvoker();
      Assert.assertEquals(invoker2,invoker);
    }
  }
}
