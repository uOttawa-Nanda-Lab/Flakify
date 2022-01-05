@Test(expected=IllegalArgumentException.class) public void testCloseIdleInvalid() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  LocalConnPool pool=new LocalConnPool(connFactory,2,2);
  pool.closeIdle(50,null);
}
