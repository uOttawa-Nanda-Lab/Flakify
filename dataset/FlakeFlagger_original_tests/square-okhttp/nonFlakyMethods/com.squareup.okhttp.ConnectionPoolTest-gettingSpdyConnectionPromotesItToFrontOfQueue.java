@Test public void gettingSpdyConnectionPromotesItToFrontOfQueue() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.share(spdyA);
  pool.recycle(httpA);
  assertPooled(pool,httpA,spdyA);
  assertSame(spdyA,pool.get(spdyAddress));
  assertPooled(pool,spdyA,httpA);
}
