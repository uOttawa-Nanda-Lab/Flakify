@Test public void testLeaseRequestTimeout() throws Exception {
  IOSession iosession1=Mockito.mock(IOSession.class);
  Mockito.when(iosession1.isClosed()).thenReturn(Boolean.TRUE);
  SessionRequest sessionRequest1=Mockito.mock(SessionRequest.class);
  Mockito.when(sessionRequest1.getAttachment()).thenReturn("somehost");
  Mockito.when(sessionRequest1.getSession()).thenReturn(iosession1);
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  Mockito.when(ioreactor.connect(Mockito.any(SocketAddress.class),Mockito.any(SocketAddress.class),Mockito.any(),Mockito.any(SessionRequestCallback.class))).thenReturn(sessionRequest1);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,1,1);
  Future<LocalPoolEntry> future1=pool.lease("somehost",null,0,TimeUnit.MILLISECONDS,null);
  Future<LocalPoolEntry> future2=pool.lease("somehost",null,0,TimeUnit.MILLISECONDS,null);
  Future<LocalPoolEntry> future3=pool.lease("somehost",null,10,TimeUnit.MILLISECONDS,null);
  pool.requestCompleted(sessionRequest1);
  Assert.assertTrue(future1.isDone());
  LocalPoolEntry entry1=future1.get();
  Assert.assertNotNull(entry1);
  Assert.assertFalse(future2.isDone());
  Assert.assertFalse(future3.isDone());
  Thread.sleep(100);
  pool.validatePendingRequests();
  Assert.assertFalse(future2.isDone());
  Assert.assertTrue(future3.isDone());
}
