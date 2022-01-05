@Test public void nonAliveConnectionNotReturned() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  httpA.close();
  assertNull(pool.get(httpAddress));
  assertPooled(pool);
}
