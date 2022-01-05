@Test public void testEmptyPool() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,10);
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(0,totals.getLeased());
  Assert.assertEquals(0,totals.getPending());
  Assert.assertEquals(10,totals.getMax());
  PoolStats stats=pool.getStats("somehost");
  Assert.assertEquals(0,stats.getAvailable());
  Assert.assertEquals(0,stats.getLeased());
  Assert.assertEquals(0,stats.getPending());
  Assert.assertEquals(2,stats.getMax());
  Assert.assertEquals("[leased: []][available: []][pending: []]",pool.toString());
}
