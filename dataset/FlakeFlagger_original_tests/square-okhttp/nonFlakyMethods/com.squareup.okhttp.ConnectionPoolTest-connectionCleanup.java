@Test public void connectionCleanup() throws IOException, InterruptedException {
  ConnectionPool pool=new ConnectionPool(10,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  pool.recycle(httpB);
  pool.share(spdyA);
  assertEquals(3,pool.getConnectionCount());
  assertEquals(2,pool.getHttpConnectionCount());
  assertEquals(1,pool.getSpdyConnectionCount());
  Util.closeQuietly(httpA);
  assertNotNull(pool.get(spdyAddress));
  Thread.sleep(50);
  assertEquals(2,pool.getConnectionCount());
  assertEquals(1,pool.getHttpConnectionCount());
  assertEquals(1,pool.getSpdyConnectionCount());
  Thread.sleep(KEEP_ALIVE_DURATION_MS);
  assertNull(pool.get(httpAddress));
  assertNull(pool.get(spdyAddress));
  Thread.sleep(50);
  assertEquals(0,pool.getConnectionCount());
  assertEquals(0,pool.getHttpConnectionCount());
  assertEquals(0,pool.getSpdyConnectionCount());
}
