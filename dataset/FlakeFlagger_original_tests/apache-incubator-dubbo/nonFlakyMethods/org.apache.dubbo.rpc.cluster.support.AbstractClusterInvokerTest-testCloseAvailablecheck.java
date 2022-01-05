@Test public void testCloseAvailablecheck(){
  LoadBalance lb=mock(LoadBalance.class);
  given(lb.select(invokers,url,invocation)).willReturn(invoker1);
  initlistsize5();
  Invoker sinvoker=cluster_nocheck.select(lb,invocation,invokers,selectedInvokers);
  Assert.assertEquals(false,sinvoker.isAvailable());
  Assert.assertEquals(invoker1,sinvoker);
}
