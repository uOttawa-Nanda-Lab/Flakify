@Test public void differentAddressConnectionNotReturned() throws Exception {
  ConnectionPool pool=new ConnectionPool(2,KEEP_ALIVE_DURATION_MS);
  pool.recycle(httpA);
  assertNull(pool.get(spdyAddress));
  assertPooled(pool,httpA);
}
