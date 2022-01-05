@Test public void testSuccessfulConnect() throws Exception {
  IOSession iosession=Mockito.mock(IOSession.class);
  SessionRequest sessionRequest=Mockito.mock(SessionRequest.class);
  Mockito.when(sessionRequest.getAttachment()).thenReturn("somehost");
  Mockito.when(sessionRequest.getSession()).thenReturn(iosession);
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  Mockito.when(ioreactor.connect(Mockito.any(SocketAddress.class),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class))).thenReturn(sessionRequest);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,10);
  Future<LocalPoolEntry> future=pool.lease("somehost",null,100,TimeUnit.MILLISECONDS,null);
  Mockito.verify(sessionRequest).setConnectTimeout(100);
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(0,totals.getLeased());
  Assert.assertEquals(1,totals.getPending());
  pool.requestCompleted(sessionRequest);
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
  LocalPoolEntry entry=future.get();
  Assert.assertNotNull(entry);
  totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(1,totals.getLeased());
  Assert.assertEquals(0,totals.getPending());
}
