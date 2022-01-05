@Test(timeout=5000) public void testConnectEventually() throws Exception {
  serverSocket.close();
  Future<Socket> connectorTask=executor.submit(connector);
  try {
    connectorTask.get(SHORT_DELAY,TimeUnit.MILLISECONDS);
    fail();
  }
 catch (  TimeoutException e) {
  }
  Exception lastException=exceptionHandler.awaitConnectionFailed(DELAY);
  assertNotNull(lastException);
  assertTrue(lastException instanceof ConnectException);
  SocketAddress address=serverSocket.getLocalSocketAddress();
  serverSocket=new ServerSocket();
  serverSocket.setReuseAddress(true);
  serverSocket.bind(address);
  Socket socket=connectorTask.get(2 * DELAY,TimeUnit.MILLISECONDS);
  assertNotNull(socket);
  assertFalse(connectorTask.isCancelled());
  socket.close();
}
