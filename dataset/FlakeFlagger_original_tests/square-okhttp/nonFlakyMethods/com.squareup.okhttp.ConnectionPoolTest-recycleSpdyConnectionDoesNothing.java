@Test public void recycleSpdyConnectionDoesNothing() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(spdyA);
  assertPooled(pool);
}
