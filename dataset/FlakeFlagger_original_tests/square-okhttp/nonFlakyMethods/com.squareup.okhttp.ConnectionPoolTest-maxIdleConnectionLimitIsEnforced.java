@Test public void maxIdleConnectionLimitIsEnforced() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  pool.recycle(httpB);
  pool.recycle(httpC);
  pool.recycle(httpD);
  assertPooled(pool,httpD,httpC);
}
