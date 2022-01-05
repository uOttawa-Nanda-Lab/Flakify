@Test public void testSelect_Invokersize2AndselectNotNull() throws Exception {
  invokers.clear();
  invokers.add(invoker2);
  invokers.add(invoker4);
  LoadBalance l=cluster.initLoadBalance(invokers,invocation);
  Assert.assertNotNull("cluster.initLoadBalance returns null!",l);
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker4);
    Invoker invoker=cluster.select(l,invocation,invokers,selectedInvokers);
    Assert.assertEquals(invoker2,invoker);
  }
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker2);
    Invoker invoker=cluster.select(l,invocation,invokers,selectedInvokers);
    Assert.assertEquals(invoker4,invoker);
  }
}
