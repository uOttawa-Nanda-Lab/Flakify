@Test public void recyclingNonAliveConnectionClosesThatConnection() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  httpA.getSocket().shutdownInput();
  pool.recycle(httpA);
  assertTrue(httpA.getSocket().isClosed());
}
