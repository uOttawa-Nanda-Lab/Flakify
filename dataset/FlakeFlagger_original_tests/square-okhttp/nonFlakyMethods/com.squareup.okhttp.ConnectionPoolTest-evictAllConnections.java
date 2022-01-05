@Test public void evictAllConnections(){
  ConnectionPool pool=new ConnectionPool(10,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  Util.closeQuietly(httpA);
  pool.recycle(httpB);
  pool.share(spdyA);
  int connectionCount=pool.getConnectionCount();
  assertTrue(connectionCount == 2 || connectionCount == 3);
  pool.evictAll();
  assertEquals(0,pool.getConnectionCount());
}
