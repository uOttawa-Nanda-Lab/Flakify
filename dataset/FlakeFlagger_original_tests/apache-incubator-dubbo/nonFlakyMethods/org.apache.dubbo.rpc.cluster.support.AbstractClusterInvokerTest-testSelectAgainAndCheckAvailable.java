@Test public void testSelectAgainAndCheckAvailable(){
  LoadBalance lb=ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(RoundRobinLoadBalance.NAME);
  initlistsize5();
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertTrue(sinvoker == invoker4);
  }
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker4);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertTrue(sinvoker == invoker2 || sinvoker == invoker4);
  }
{
    for (int i=0; i < 100; i++) {
      selectedInvokers.clear();
      Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
      Assert.assertTrue(sinvoker == invoker2 || sinvoker == invoker4);
    }
  }
{
    for (int i=0; i < 100; i++) {
      selectedInvokers.clear();
      selectedInvokers.add(invoker1);
      selectedInvokers.add(invoker3);
      selectedInvokers.add(invoker5);
      Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
      Assert.assertTrue(sinvoker == invoker2 || sinvoker == invoker4);
    }
  }
{
    for (int i=0; i < 100; i++) {
      selectedInvokers.clear();
      selectedInvokers.add(invoker1);
      selectedInvokers.add(invoker3);
      selectedInvokers.add(invoker2);
      selectedInvokers.add(invoker4);
      selectedInvokers.add(invoker5);
      Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
      Assert.assertTrue(sinvoker == invoker2 || sinvoker == invoker4);
    }
  }
}
