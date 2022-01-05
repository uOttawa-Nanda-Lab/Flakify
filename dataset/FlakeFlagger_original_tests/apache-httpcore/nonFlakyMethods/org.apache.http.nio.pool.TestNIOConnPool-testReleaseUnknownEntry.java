@Test public void testReleaseUnknownEntry() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,2);
  pool.release(new LocalPoolEntry("somehost",Mockito.mock(IOSession.class)),true);
}
