@Test public void testCloseFrame() throws Exception {
  if (getVersion() == WebSocketVersion.V00) {
    return;
  }
  final CountDownLatch latch=new CountDownLatch(1);
  final AtomicBoolean connected=new AtomicBoolean(false);
  DefaultServer.setRootHandler(new WebSocketProtocolHandshakeHandler(new WebSocketConnectionCallback(){
    @Override public void onConnect(    final WebSocketHttpExchange exchange,    final WebSocketChannel channel){
      connected.set(true);
      channel.getReceiveSetter().set(new ChannelListener<WebSocketChannel>(){
        @Override public void handleEvent(        final WebSocketChannel channel){
          try {
            final StreamSourceFrameChannel ws=channel.receive();
            if (ws == null) {
              return;
            }
            Assert.assertEquals(WebSocketFrameType.CLOSE,ws.getType());
            channel.close();
            channel.getReceiveSetter().set(null);
            latch.countDown();
          }
 catch (          IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
);
      channel.resumeReceives();
    }
  }
));
  final AtomicBoolean receivedResponse=new AtomicBoolean(false);
  WebSocketTestClient client=new WebSocketTestClient(getVersion(),new URI("ws://" + NetworkUtils.formatPossibleIpv6Address(DefaultServer.getHostAddress("default")) + ":"+ DefaultServer.getHostPort("default")+ "/"));
  client.connect();
  client.send(new CloseWebSocketFrame(),new WebSocketTestClient.FrameListener(){
    @Override public void onFrame(    WebSocketFrame frame){
      receivedResponse.set(true);
    }
    @Override public void onError(    Throwable t){
      t.printStackTrace();
    }
  }
);
  latch.await();
  Assert.assertFalse(receivedResponse.get());
  client.destroy();
}
