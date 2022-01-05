@Test public void testJoin() throws RemotingException, InterruptedException, IOException {
  final String groupURL="file://" + folder.newFile();
  FileNetworker networker=new FileNetworker();
  Group group=networker.lookup(URL.valueOf(groupURL));
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  Peer peer1=group.join(URL.valueOf("dubbo://0.0.0.0:" + NetUtils.getAvailablePort()),new ChannelHandlerAdapter(){
    @Override public void received(    Channel channel,    Object message){
      countDownLatch.countDown();
    }
  }
);
  Peer peer2=group.join(URL.valueOf("dubbo://0.0.0.0:" + NetUtils.getAvailablePort()),mock(ChannelHandlerAdapter.class));
  while (true) {
    long count=countDownLatch.getCount();
    if (count > 0) {
      break;
    }
    for (    Channel channel : peer1.getChannels()) {
      channel.send(0,false);
      channel.send("hello world!");
    }
    TimeUnit.MILLISECONDS.sleep(50);
  }
  peer2.close();
  peer1.close();
}
