@Test public void testConnectionFails() throws Exception {
  serverSocket.close();
  Future<Socket> connectorTask=executor.submit(connector);
  try {
    connectorTask.get(SHORT_DELAY,TimeUnit.MILLISECONDS);
    fail();
  }
 catch (  TimeoutException e) {
  }
  Exception lastException=exceptionHandler.awaitConnectionFailed(DELAY);
  assertTrue(lastException instanceof ConnectException);
  assertFalse(connectorTask.isDone());
  connectorTask.cancel(true);
  assertTrue(connectorTask.isCancelled());
}
