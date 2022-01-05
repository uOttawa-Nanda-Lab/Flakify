@Test public void testSubscribeAnyValue() throws InterruptedException {
  final CountDownLatch latch=new CountDownLatch(1);
  zookeeperRegistry.register(serviceUrl);
  zookeeperRegistry.subscribe(anyUrl,new NotifyListener(){
    @Override public void notify(    List<URL> urls){
      latch.countDown();
    }
  }
);
  zookeeperRegistry.register(serviceUrl);
  latch.await();
}
