@Test(timeout=2000) public void testIssue() throws Exception {
  int port=SocketUtil.getAvailablePort();
  final WebSocketClient webSocket=new WebSocketClient(new URI("ws://localhost:" + port)){
    @Override public void onOpen(    ServerHandshake handshakedata){
      try {
        this.reconnect();
        Assert.fail("Exception should be thrown");
      }
 catch (      IllegalStateException e) {
      }
    }
    @Override public void onMessage(    String message){
      try {
        this.reconnect();
        Assert.fail("Exception should be thrown");
      }
 catch (      IllegalStateException e) {
        send("hi");
      }
    }
    @Override public void onClose(    int code,    String reason,    boolean remote){
      try {
        this.reconnect();
        Assert.fail("Exception should be thrown");
      }
 catch (      IllegalStateException e) {
      }
    }
    @Override public void onError(    Exception ex){
      try {
        this.reconnect();
        Assert.fail("Exception should be thrown");
      }
 catch (      IllegalStateException e) {
      }
    }
  }
;
  WebSocketServer server=new WebSocketServer(new InetSocketAddress(port)){
    @Override public void onOpen(    WebSocket conn,    ClientHandshake handshake){
      conn.send("hi");
    }
    @Override public void onClose(    WebSocket conn,    int code,    String reason,    boolean remote){
      countServerDownLatch.countDown();
    }
    @Override public void onMessage(    WebSocket conn,    String message){
      conn.close();
    }
    @Override public void onError(    WebSocket conn,    Exception ex){
      fail("There should be no onError!");
    }
    @Override public void onStart(){
      webSocket.connect();
    }
  }
;
  server.start();
  countServerDownLatch.await();
  server.stop();
}
