@Test public void idleConnectionNotReturned() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  Thread.sleep(KEEP_ALIVE_DURATION_MS * 2);
  assertNull(pool.get(httpAddress));
  assertPooled(pool);
}
