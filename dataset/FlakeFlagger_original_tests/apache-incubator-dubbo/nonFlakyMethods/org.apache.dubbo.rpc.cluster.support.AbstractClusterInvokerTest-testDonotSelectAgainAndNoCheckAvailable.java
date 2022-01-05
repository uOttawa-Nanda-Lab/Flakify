@Test public void testDonotSelectAgainAndNoCheckAvailable(){
  LoadBalance lb=ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(RoundRobinLoadBalance.NAME);
  initlistsize5();
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker4);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster_nocheck.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertSame(invoker1,sinvoker);
  }
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker4);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster_nocheck.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertSame(invoker2,sinvoker);
  }
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker4);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster_nocheck.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertSame(invoker3,sinvoker);
  }
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker4);
    Invoker sinvoker=cluster_nocheck.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertSame(invoker5,sinvoker);
  }
{
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker4);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster_nocheck.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertTrue(invokers.contains(sinvoker));
  }
}
