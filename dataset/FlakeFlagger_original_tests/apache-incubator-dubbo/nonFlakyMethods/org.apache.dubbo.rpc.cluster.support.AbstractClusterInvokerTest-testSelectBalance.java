/** 
 * Test balance.
 */
@Test public void testSelectBalance(){
  LoadBalance lb=ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(RoundRobinLoadBalance.NAME);
  initlistsize5();
  Map<Invoker,AtomicLong> counter=new ConcurrentHashMap<Invoker,AtomicLong>();
  for (  Invoker invoker : invokers) {
    counter.put(invoker,new AtomicLong(0));
  }
  int runs=1000;
  for (int i=0; i < runs; i++) {
    selectedInvokers.clear();
    Invoker sinvoker=cluster.select(lb,invocation,invokers,selectedInvokers);
    counter.get(sinvoker).incrementAndGet();
  }
  for (  Invoker minvoker : counter.keySet()) {
    Long count=counter.get(minvoker).get();
    if (minvoker.isAvailable())     Assert.assertTrue("count should > avg",count > runs / invokers.size());
  }
  Assert.assertEquals(runs,counter.get(invoker2).get() + counter.get(invoker4).get());
  ;
}
