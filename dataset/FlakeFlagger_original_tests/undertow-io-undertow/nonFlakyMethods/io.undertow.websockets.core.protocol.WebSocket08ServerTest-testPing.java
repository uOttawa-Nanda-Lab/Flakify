@Test public void testPing() throws Exception {
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
            Assert.assertEquals(WebSocketFrameType.PING,ws.getType());
            ByteBuffer buf=ByteBuffer.allocate(32);
            while (ws.read(buf) != -1) {
            }
            buf.flip();
            StreamSinkFrameChannel sink=channel.send(WebSocketFrameType.PONG,buf.remaining());
            Assert.assertEquals(WebSocketFrameType.PONG,sink.getType());
            while (buf.hasRemaining()) {
              sink.write(buf);
            }
            sink.shutdownWrites();
            if (!sink.flush()) {
              sink.getWriteSetter().set(ChannelListeners.flushingChannelListener(null,null));
              sink.resumeWrites();
            }
            channel.getReceiveSetter().set(null);
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
  final FutureResult latch=new FutureResult();
  final byte[] payload="payload".getBytes();
  WebSocketTestClient client=new WebSocketTestClient(getVersion(),new URI("ws://" + DefaultServer.getHostAddress("default") + ':'+ DefaultServer.getHostPort("default")+ '/'));
  client.connect();
  client.send(new PingWebSocketFrame(ChannelBuffers.wrappedBuffer(payload)),new FrameChecker(PongWebSocketFrame.class,payload,latch));
  latch.getIoFuture().get();
  client.destroy();
}
