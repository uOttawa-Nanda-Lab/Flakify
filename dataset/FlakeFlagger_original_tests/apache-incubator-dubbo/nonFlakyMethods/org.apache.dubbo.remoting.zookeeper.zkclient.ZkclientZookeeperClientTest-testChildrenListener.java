@Test public void testChildrenListener() throws InterruptedException {
  String path="/dubbo/org.apache.dubbo.demo.DemoService/providers";
  zkclientZookeeperClient.create(path,false);
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  zkclientZookeeperClient.addTargetChildListener(path,new IZkChildListener(){
    @Override public void handleChildChange(    String s,    List<String> list) throws Exception {
      countDownLatch.countDown();
    }
  }
);
  zkclientZookeeperClient.createPersistent(path + "/provider1");
  countDownLatch.await();
}
