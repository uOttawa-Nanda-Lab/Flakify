@Test public void testDoRetry_nofify() throws Exception {
  final AtomicInteger count=new AtomicInteger(0);
  NotifyListener listner=new NotifyListener(){
    @Override public void notify(    List<URL> urls){
      count.incrementAndGet();
      if (count.get() == 1l) {
        throw new RuntimeException("test exception please ignore");
      }
    }
  }
;
  registry=new MockRegistry(registryUrl,new CountDownLatch(0));
  registry.subscribe(serviceUrl.setProtocol(Constants.CONSUMER_PROTOCOL).addParameters(CollectionUtils.toStringMap("check","false")),listner);
  assertEquals(1,count.get());
  for (int i=0; i < trytimes; i++) {
    System.out.println("failback notify retry ,times:" + i);
    if (count.get() == 2)     break;
    Thread.sleep(sleeptime);
  }
  assertEquals(2,count.get());
}
