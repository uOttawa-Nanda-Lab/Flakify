@Test public void poolSingleHttpConnection() throws IOException {
  ConnectionPool pool=new ConnectionPool(1,KEEP_ALIVE_DURATION_MS);
  Connection connection=pool.get(httpAddress);
  assertNull(connection);
  connection=new Connection(null,new Route(httpAddress,Proxy.NO_PROXY,httpSocketAddress,true));
  connection.connect(200,200,null);
  assertEquals(0,pool.getConnectionCount());
  pool.recycle(connection);
  assertEquals(1,pool.getConnectionCount());
  assertEquals(1,pool.getHttpConnectionCount());
  assertEquals(0,pool.getSpdyConnectionCount());
  Connection recycledConnection=pool.get(httpAddress);
  assertEquals(connection,recycledConnection);
  assertTrue(recycledConnection.isAlive());
  recycledConnection=pool.get(httpAddress);
  assertNull(recycledConnection);
}
