@Test public void testEmptyPool() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  LocalConnPool pool=new LocalConnPool(connFactory,2,10);
  pool.setDefaultMaxPerRoute(5);
  pool.setMaxPerRoute("somehost",3);
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(0,totals.getLeased());
  Assert.assertEquals(10,totals.getMax());
  PoolStats stats=pool.getStats("somehost");
  Assert.assertEquals(0,stats.getAvailable());
  Assert.assertEquals(0,stats.getLeased());
  Assert.assertEquals(3,stats.getMax());
  Assert.assertEquals("[leased: []][available: []][pending: []]",pool.toString());
}
