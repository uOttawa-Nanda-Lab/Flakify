@Test public void testShutdown() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,2);
  pool.shutdown(1000);
  Mockito.verify(ioreactor,Mockito.times(1)).shutdown(1000);
  pool.shutdown(1000);
  Mockito.verify(ioreactor,Mockito.times(1)).shutdown(1000);
  try {
    pool.lease("somehost",null);
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException expected) {
  }
  pool.release(new LocalPoolEntry("somehost",Mockito.mock(IOSession.class)),true);
  pool.requestCompleted(Mockito.mock(SessionRequest.class));
  pool.requestFailed(Mockito.mock(SessionRequest.class));
  pool.requestCancelled(Mockito.mock(SessionRequest.class));
  pool.requestTimeout(Mockito.mock(SessionRequest.class));
}
