@Test public void testSelect_Invokersize0() throws Exception {
  LoadBalance l=cluster.initLoadBalance(invokers,invocation);
  Assert.assertNotNull("cluster.initLoadBalance returns null!",l);
{
    Invoker invoker=cluster.select(l,null,null,null);
    Assert.assertEquals(null,invoker);
  }
{
    invokers.clear();
    selectedInvokers.clear();
    Invoker invoker=cluster.select(l,null,invokers,null);
    Assert.assertEquals(null,invoker);
  }
}
