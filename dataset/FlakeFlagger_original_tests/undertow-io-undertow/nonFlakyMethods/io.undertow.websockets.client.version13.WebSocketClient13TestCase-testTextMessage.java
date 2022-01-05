@Test public void testTextMessage() throws Exception {
  final WebSocketChannel webSocketChannel=WebSocketClient.connect(worker,buffer,OptionMap.EMPTY,new URI(DefaultServer.getDefaultServerURL()),WebSocketVersion.V13).get();
  final CountDownLatch latch=new CountDownLatch(1);
  final AtomicReference<String> result=new AtomicReference<String>();
  webSocketChannel.getReceiveSetter().set(new ChannelListener<WebSocketChannel>(){
    @Override public void handleEvent(    final WebSocketChannel channel){
      ChannelInputStream stream=null;
      try {
        final StreamSourceFrameChannel r=channel.receive();
        if (r != null) {
          stream=new ChannelInputStream(r);
          result.set(FileUtils.readFile(stream));
          latch.countDown();
        }
      }
 catch (      IOException e) {
        e.printStackTrace();
        latch.countDown();
      }
 finally {
        IoUtils.safeClose(stream);
      }
    }
  }
);
  webSocketChannel.resumeReceives();
  StreamSinkFrameChannel sendChannel=webSocketChannel.send(WebSocketFrameType.TEXT,11);
  new StringWriteChannelListener("Hello World").setup(sendChannel);
  latch.await(10,TimeUnit.SECONDS);
  Assert.assertEquals("Hello World",result.get());
  webSocketChannel.sendClose();
}
