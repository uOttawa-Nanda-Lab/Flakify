public void testSelect_multiInvokers(String lbname) throws Exception {
  int min=1000, max=5000;
  Double d=(Math.random() * (max - min + 1) + min);
  int runs=d.intValue();
  Assert.assertTrue(runs > min);
  LoadBalance lb=ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(lbname);
  initlistsize5();
  for (int i=0; i < runs; i++) {
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertEquals(true,sinvoker.isAvailable());
    Mockito.clearInvocations(invoker1,invoker2,invoker3,invoker4,invoker5);
  }
  for (int i=0; i < runs; i++) {
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertEquals(true,sinvoker.isAvailable());
    Mockito.clearInvocations(invoker1,invoker2,invoker3,invoker4,invoker5);
  }
  for (int i=0; i < runs; i++) {
    selectedInvokers.clear();
    selectedInvokers.add(invoker2);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertEquals(true,sinvoker.isAvailable());
    Mockito.clearInvocations(invoker1,invoker2,invoker3,invoker4,invoker5);
  }
  for (int i=0; i < runs; i++) {
    selectedInvokers.clear();
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker4);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertEquals(true,sinvoker.isAvailable());
    Mockito.clearInvocations(invoker1,invoker2,invoker3,invoker4,invoker5);
  }
  for (int i=0; i < runs; i++) {
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker3);
    selectedInvokers.add(invoker5);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertEquals(true,sinvoker.isAvailable());
    Mockito.clearInvocations(invoker1,invoker2,invoker3,invoker4,invoker5);
  }
  for (int i=0; i < runs; i++) {
    selectedInvokers.clear();
    selectedInvokers.add(invoker1);
    selectedInvokers.add(invoker2);
    selectedInvokers.add(invoker3);
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    Assert.assertEquals(true,sinvoker.isAvailable());
    Mockito.clearInvocations(invoker1,invoker2,invoker3,invoker4,invoker5);
  }
}
