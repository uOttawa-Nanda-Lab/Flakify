@Test public void testConnectState() throws Exception {
  assertThat(zkclientZookeeperClient.isConnected(),is(true));
  final CountDownLatch stopLatch=new CountDownLatch(1);
  zkclientZookeeperClient.addStateListener(new StateListener(){
    @Override public void stateChanged(    int connected){
      stopLatch.countDown();
    }
  }
);
  zkServer.stop();
  stopLatch.await();
  assertThat(zkclientZookeeperClient.isConnected(),is(false));
}
