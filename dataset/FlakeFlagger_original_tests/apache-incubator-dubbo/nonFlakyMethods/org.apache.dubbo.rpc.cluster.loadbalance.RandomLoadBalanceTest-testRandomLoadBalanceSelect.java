@Test public void testRandomLoadBalanceSelect(){
  int runs=1000;
  Map<Invoker,AtomicLong> counter=getInvokeCounter(runs,RandomLoadBalance.NAME);
  for (  Invoker minvoker : counter.keySet()) {
    Long count=counter.get(minvoker).get();
    Assert.assertTrue("abs diff should < avg",Math.abs(count - runs / (0f + invokers.size())) < runs / (0f + invokers.size()));
  }
  for (int i=0; i < 5; i++) {
    for (int j=0; j <= i; j++) {
      RpcStatus.beginCount(invokers.get(i).getUrl(),invocation.getMethodName());
    }
  }
  counter=getInvokeCounter(runs,LeastActiveLoadBalance.NAME);
  for (  Invoker minvoker : counter.keySet()) {
    Long count=counter.get(minvoker).get();
  }
  Assert.assertEquals(runs,counter.get(invoker1).intValue());
  Assert.assertEquals(0,counter.get(invoker2).intValue());
  Assert.assertEquals(0,counter.get(invoker3).intValue());
  Assert.assertEquals(0,counter.get(invoker4).intValue());
  Assert.assertEquals(0,counter.get(invoker5).intValue());
}
