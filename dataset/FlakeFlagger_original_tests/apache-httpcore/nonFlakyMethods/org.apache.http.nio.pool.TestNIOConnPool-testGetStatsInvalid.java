@Test(expected=IllegalArgumentException.class) public void testGetStatsInvalid() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,2);
  pool.getStats(null);
}
