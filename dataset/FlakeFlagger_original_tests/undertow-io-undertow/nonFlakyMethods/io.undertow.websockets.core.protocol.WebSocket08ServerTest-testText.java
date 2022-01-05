@org.junit.Test public void testText() throws Exception {
  if (getVersion() == WebSocketVersion.V00) {
    return;
  }
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
            new StringReadChannelListener(exchange.getBufferPool()){
              @Override protected void stringDone(              final String string){
                try {
                  if (string.equals("hello")) {
                    new StringWriteChannelListener("world").setup(channel.send(WebSocketFrameType.TEXT,"world".length()));
                  }
 else {
                    new StringWriteChannelListener(string).setup(channel.send(WebSocketFrameType.TEXT,string.length()));
                  }
                }
 catch (                IOException e) {
                  e.printStackTrace();
                  throw new RuntimeException(e);
                }
              }
              @Override protected void error(              final IOException e){
                try {
                  e.printStackTrace();
                  new StringWriteChannelListener("ERROR").setup(channel.send(WebSocketFrameType.TEXT,"ERROR".length()));
                }
 catch (                IOException ex) {
                  ex.printStackTrace();
                  throw new RuntimeException(ex);
                }
              }
            }
.setup(ws);
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
  final AtomicReference<String> result=new AtomicReference<String>();
  final FutureResult<?> latch=new FutureResult();
  WebSocketTestClient client=new WebSocketTestClient(getVersion(),new URI("ws://" + NetworkUtils.formatPossibleIpv6Address(DefaultServer.getHostAddress("default")) + ":"+ DefaultServer.getHostPort("default")+ "/"));
  client.connect();
  client.send(new TextWebSocketFrame(ChannelBuffers.copiedBuffer("hello",CharsetUtil.US_ASCII)),new FrameChecker(TextWebSocketFrame.class,"world".getBytes(CharsetUtil.US_ASCII),latch));
  latch.getIoFuture().get();
  client.destroy();
}
