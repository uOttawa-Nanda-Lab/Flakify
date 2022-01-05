@Test public void shouldConnectToNetty4Server() throws Exception {
  final CountDownLatch lock=new CountDownLatch(1);
  int port=NetUtils.getAvailablePort();
  URL url=new URL("http","localhost",port,new String[]{Constants.BIND_PORT_KEY,String.valueOf(port)});
  new NettyTransporter().bind(url,new ChannelHandlerAdapter(){
    @Override public void connected(    Channel channel) throws RemotingException {
      lock.countDown();
    }
  }
);
  new NettyTransporter().connect(url,new ChannelHandlerAdapter(){
    @Override public void sent(    Channel channel,    Object message) throws RemotingException {
      channel.send(message);
      channel.close();
    }
  }
);
  lock.await();
}
