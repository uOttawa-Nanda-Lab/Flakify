@Test public void shareHttpConnectionFails() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  try {
    pool.share(httpA);
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
  assertPooled(pool);
}
