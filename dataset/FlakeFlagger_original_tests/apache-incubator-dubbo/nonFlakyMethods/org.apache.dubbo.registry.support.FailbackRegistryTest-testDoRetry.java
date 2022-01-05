/** 
 * Test method for {@link org.apache.dubbo.registry.support.FailbackRegistry#retry()}.
 * @throws Exception
 */
@Test public void testDoRetry() throws Exception {
  final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);
  final CountDownLatch latch=new CountDownLatch(3);
  NotifyListener listner=new NotifyListener(){
    @Override public void notify(    List<URL> urls){
      notified.set(Boolean.TRUE);
    }
  }
;
  registry=new MockRegistry(registryUrl,latch);
  registry.setBad(true);
  registry.register(serviceUrl);
  registry.unregister(serviceUrl);
  registry.subscribe(serviceUrl.setProtocol(Constants.CONSUMER_PROTOCOL).addParameters(CollectionUtils.toStringMap("check","false")),listner);
  registry.unsubscribe(serviceUrl.setProtocol(Constants.CONSUMER_PROTOCOL).addParameters(CollectionUtils.toStringMap("check","false")),listner);
  assertEquals(false,notified.get());
  assertEquals(3,latch.getCount());
  registry.setBad(false);
  for (int i=0; i < trytimes; i++) {
    System.out.println("failback registry retry ,times:" + i);
    if (latch.getCount() == 0)     break;
    Thread.sleep(sleeptime);
  }
  assertEquals(0,latch.getCount());
  assertEquals(false,notified.get());
}
