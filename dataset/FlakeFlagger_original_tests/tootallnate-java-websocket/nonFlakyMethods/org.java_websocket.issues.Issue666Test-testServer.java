@Test public void testServer() throws Exception {
  Map<Long,Thread> mapBefore=ThreadCheck.getThreadMap();
  int port=SocketUtil.getAvailablePort();
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
  Map<Long,Thread> mapAfter=ThreadCheck.getThreadMap();
  for (  long key : mapBefore.keySet()) {
    mapAfter.remove(key);
  }
  for (  Thread thread : mapAfter.values()) {
    String name=thread.getName();
    if (!name.startsWith("WebSocketSelector-") && !name.startsWith("WebSocketWorker-") && !name.equals("WebSocketTimer")) {
      Assert.fail("Thread not correctly named! Is: " + name);
    }
  }
  server.stop();
}
