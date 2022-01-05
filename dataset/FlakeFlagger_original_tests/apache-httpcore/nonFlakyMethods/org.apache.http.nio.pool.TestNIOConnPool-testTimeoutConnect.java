@Test public void testTimeoutConnect() throws Exception {
  IOSession iosession=Mockito.mock(IOSession.class);
  SessionRequest sessionRequest=Mockito.mock(SessionRequest.class);
  Mockito.when(sessionRequest.getAttachment()).thenReturn("somehost");
  Mockito.when(sessionRequest.getSession()).thenReturn(iosession);
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  Mockito.when(ioreactor.connect(Mockito.any(SocketAddress.class),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class))).thenReturn(sessionRequest);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,10);
  Future<LocalPoolEntry> future=pool.lease("somehost",null);
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(0,totals.getLeased());
  Assert.assertEquals(1,totals.getPending());
  pool.requestTimeout(sessionRequest);
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
  try {
    future.get();
    Assert.fail("ExecutionException should have been thrown");
  }
 catch (  ExecutionException ex) {
    Assert.assertTrue(ex.getCause() instanceof SocketTimeoutException);
  }
  totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(0,totals.getLeased());
  Assert.assertEquals(0,totals.getPending());
}
