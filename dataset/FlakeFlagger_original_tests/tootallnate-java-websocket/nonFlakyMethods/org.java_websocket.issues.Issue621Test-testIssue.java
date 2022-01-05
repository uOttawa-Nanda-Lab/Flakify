@Test public void testIssue() throws Exception {
  System.setErr(new TestPrintStream(System.err));
  int port=SocketUtil.getAvailablePort();
  WebSocketClient webSocket=new WebSocketClient(new URI("ws://localhost:" + port)){
    @Override public void onOpen(    ServerHandshake handshakedata){
    }
    @Override public void onMessage(    String message){
    }
    @Override public void onClose(    int code,    String reason,    boolean remote){
      countDownLatch.countDown();
    }
    @Override public void onError(    Exception ex){
    }
  }
;
  WebSocketServer server=new WebSocketServer(new InetSocketAddress(port)){
    @Override public void onOpen(    WebSocket conn,    ClientHandshake handshake){
      conn.close();
    }
    @Override public void onClose(    WebSocket conn,    int code,    String reason,    boolean remote){
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
  countDownLatch.await();
  assertTrue("There was an error using System.err",!wasError);
  server.stop();
}
