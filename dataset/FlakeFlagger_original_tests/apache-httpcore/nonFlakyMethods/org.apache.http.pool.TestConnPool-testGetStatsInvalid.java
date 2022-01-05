@Test(expected=IllegalArgumentException.class) public void testGetStatsInvalid() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  LocalConnPool pool=new LocalConnPool(connFactory,2,2);
  pool.getStats(null);
}
