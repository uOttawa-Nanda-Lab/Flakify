@Test public void testJoin() throws RemotingException, InterruptedException {
  final String groupURL="multicast://224.5.6.7:1234";
  final String peerURL="dubbo://0.0.0.0:" + NetUtils.getAvailablePort();
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  Peer peer1=Networkers.join(groupURL,peerURL,new ChannelHandlerAdapter(){
    @Override public void received(    Channel channel,    Object message){
      countDownLatch.countDown();
    }
  }
);
  Peer peer2=Networkers.join(groupURL,"dubbo://0.0.0.0:" + NetUtils.getAvailablePort(),mock(ChannelHandlerAdapter.class));
  while (true) {
    long count=countDownLatch.getCount();
    if (count > 0) {
      break;
    }
    for (    Channel channel : peer1.getChannels()) {
      channel.send("hello world!");
    }
    TimeUnit.MILLISECONDS.sleep(50);
  }
  Group lookup=Networkers.lookup(groupURL);
  assertThat(lookup,not(nullValue()));
  peer2.close();
  peer1.close();
}
