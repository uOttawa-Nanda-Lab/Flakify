@Test public void testReleaseUnknownEntry() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  LocalConnPool pool=new LocalConnPool(connFactory,2,10);
  pool.release(new LocalPoolEntry("somehost",Mockito.mock(HttpConnection.class)),true);
}
