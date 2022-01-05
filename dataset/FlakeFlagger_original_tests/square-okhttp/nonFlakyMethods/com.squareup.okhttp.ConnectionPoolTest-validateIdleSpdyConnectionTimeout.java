@Test public void validateIdleSpdyConnectionTimeout() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.share(spdyA);
  Thread.sleep((int)(KEEP_ALIVE_DURATION_MS * 0.7));
  assertNull(pool.get(httpAddress));
  assertPooled(pool,spdyA);
  Thread.sleep((int)(KEEP_ALIVE_DURATION_MS * 0.4));
  assertNull(pool.get(httpAddress));
  assertPooled(pool);
}
