@Test public void testRoundRobinLoadBalanceSelect(){
  int runs=10000;
  Map<Invoker,AtomicLong> counter=getInvokeCounter(runs,RoundRobinLoadBalance.NAME);
  for (  Invoker minvoker : counter.keySet()) {
    Long count=counter.get(minvoker).get();
    Assert.assertTrue("abs diff should < 1",Math.abs(count - runs / (0f + invokers.size())) < 1f);
  }
}
