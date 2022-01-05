@Test public void testJoin() throws RemotingException, InterruptedException {
  final String groupURL="multicast://224.5.6.7:1234";
  MulticastExchangeNetworker multicastExchangeNetworker=new MulticastExchangeNetworker();
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  Peer peer1=multicastExchangeNetworker.lookup(URL.valueOf(groupURL)).join(URL.valueOf("dubbo://0.0.0.0:" + NetUtils.getAvailablePort()),new ExchangeHandlerAdapter(){
    @Override public CompletableFuture<Object> reply(    ExchangeChannel channel,    Object msg) throws RemotingException {
      countDownLatch.countDown();
      return super.reply(channel,msg);
    }
  }
);
  Peer peer2=multicastExchangeNetworker.lookup(URL.valueOf(groupURL)).join(URL.valueOf("dubbo://0.0.0.0:" + NetUtils.getAvailablePort()),mock(ExchangeHandler.class));
  while (true) {
    for (    Channel channel : peer1.getChannels()) {
      channel.send("hello multicast exchange network!");
    }
    TimeUnit.MILLISECONDS.sleep(50);
    long count=countDownLatch.getCount();
    if (count > 0) {
      break;
    }
  }
  Group lookup=Networkers.lookup(groupURL);
  assertThat(lookup,not(nullValue()));
  assertThat(peer1,instanceOf(ExchangeServerPeer.class));
  peer1.close();
  peer2.close();
}
