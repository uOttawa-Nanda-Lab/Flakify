@Test public void testSelect_Invokersize1() throws Exception {
  invokers.clear();
  invokers.add(invoker1);
  LoadBalance l=cluster.initLoadBalance(invokers,invocation);
  Assert.assertNotNull("cluster.initLoadBalance returns null!",l);
  Invoker invoker=cluster.select(l,null,invokers,null);
  Assert.assertEquals(invoker1,invoker);
}
