@Test public void validateIdleHttpConnectionTimeout() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  Thread.sleep((int)(KEEP_ALIVE_DURATION_MS * 0.7));
  assertNull(pool.get(spdyAddress));
  assertPooled(pool,httpA);
  Thread.sleep((int)(KEEP_ALIVE_DURATION_MS * 0.4));
  assertNull(pool.get(spdyAddress));
  assertPooled(pool);
}
