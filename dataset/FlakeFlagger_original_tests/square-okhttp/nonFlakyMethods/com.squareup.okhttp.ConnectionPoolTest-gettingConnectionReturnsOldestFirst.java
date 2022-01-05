@Test public void gettingConnectionReturnsOldestFirst() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  pool.recycle(httpB);
  assertSame(httpA,pool.get(httpAddress));
}
