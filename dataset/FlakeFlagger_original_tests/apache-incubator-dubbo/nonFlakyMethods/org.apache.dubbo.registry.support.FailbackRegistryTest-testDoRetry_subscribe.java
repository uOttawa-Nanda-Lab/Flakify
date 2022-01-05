@Test public void testDoRetry_subscribe() throws Exception {
  final CountDownLatch latch=new CountDownLatch(1);
  registry=new MockRegistry(registryUrl,latch);
  registry.setBad(true);
  registry.register(serviceUrl);
  registry.setBad(false);
  for (int i=0; i < trytimes; i++) {
    System.out.println("failback registry retry ,times:" + i);
    if (latch.getCount() == 0)     break;
    Thread.sleep(sleeptime);
  }
  assertEquals(0,latch.getCount());
}
