@Test public void testIssue() throws Exception {
  int port=SocketUtil.getAvailablePort();
  WebSocketClient webSocket=new WebSocketClient(new URI("ws://localhost:" + port)){
    @Override public void onOpen(    ServerHandshake handshakedata){
    }
    @Override public void onMessage(    String message){
    }
    @Override public void onClose(    int code,    String reason,    boolean remote){
      wasOpenClient=isOpen();
      countDownLatch.countDown();
    }
    @Override public void onError(    Exception ex){
    }
  }
;
  WebSocketServer server=new WebSocketServer(new InetSocketAddress(port)){
    @Override public void onOpen(    WebSocket conn,    ClientHandshake handshake){
    }
    @Override public void onClose(    WebSocket conn,    int code,    String reason,    boolean remote){
      wasOpenServer=conn.isOpen();
    }
    @Override public void onMessage(    WebSocket conn,    String message){
    }
    @Override public void onError(    WebSocket conn,    Exception ex){
    }
    @Override public void onStart(){
      countServerDownLatch.countDown();
    }
  }
;
  server.start();
  countServerDownLatch.await();
  webSocket.connectBlocking();
  assertTrue("webSocket.isOpen()",webSocket.isOpen());
  webSocket.getSocket().close();
  countDownLatch.await();
  assertTrue("!webSocket.isOpen()",!webSocket.isOpen());
  assertTrue("!wasOpenClient",!wasOpenClient);
  assertTrue("!wasOpenServer",!wasOpenServer);
  server.stop();
}
