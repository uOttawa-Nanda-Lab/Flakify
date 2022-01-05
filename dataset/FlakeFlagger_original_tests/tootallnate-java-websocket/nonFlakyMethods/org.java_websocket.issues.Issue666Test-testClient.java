@Test public void testClient() throws Exception {
  int port=SocketUtil.getAvailablePort();
  WebSocketClient client=new WebSocketClient(new URI("ws://localhost:" + port)){
    @Override public void onOpen(    ServerHandshake handshakedata){
    }
    @Override public void onMessage(    String message){
    }
    @Override public void onClose(    int code,    String reason,    boolean remote){
    }
    @Override public void onError(    Exception ex){
    }
  }
;
  WebSocketServer server=new WebSocketServer(new InetSocketAddress(port)){
    @Override public void onOpen(    WebSocket conn,    ClientHandshake handshake){
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
  Map<Long,Thread> mapBefore=ThreadCheck.getThreadMap();
  client.connectBlocking();
  Map<Long,Thread> mapAfter=ThreadCheck.getThreadMap();
  for (  long key : mapBefore.keySet()) {
    mapAfter.remove(key);
  }
  for (  Thread thread : mapAfter.values()) {
    String name=thread.getName();
    if (!name.equals("WebSocketTimer") && !name.startsWith("WebSocketWriteThread-") && !name.startsWith("WebSocketConnectReadThread-")) {
      Assert.fail("Thread not correctly named! Is: " + name);
    }
  }
  client.close();
  server.stop();
}
