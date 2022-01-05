@Test public void getSpdyConnection() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.share(spdyA);
  assertSame(spdyA,pool.get(spdyAddress));
  assertPooled(pool,spdyA);
}
