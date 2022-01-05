@Test public void expiredConnectionsAreEvicted() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  pool.recycle(httpB);
  Thread.sleep(2 * KEEP_ALIVE_DURATION_MS);
  pool.get(spdyAddress);
  assertPooled(pool);
}
