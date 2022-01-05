@Test public void getHttpConnection() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  assertSame(httpA,pool.get(httpAddress));
  assertPooled(pool);
}
