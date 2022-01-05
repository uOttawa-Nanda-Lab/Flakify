@Test public void testChildrenListener() throws InterruptedException {
  String path="/dubbo/org.apache.dubbo.demo.DemoService/providers";
  curatorClient.create(path,false);
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  curatorClient.addTargetChildListener(path,new CuratorWatcher(){
    @Override public void process(    WatchedEvent watchedEvent) throws Exception {
      countDownLatch.countDown();
    }
  }
);
  curatorClient.createPersistent(path + "/provider1");
  countDownLatch.await();
}
