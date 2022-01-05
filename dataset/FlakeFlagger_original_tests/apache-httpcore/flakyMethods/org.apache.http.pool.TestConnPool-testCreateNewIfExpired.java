@Test public void testCreateNewIfExpired() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  HttpConnection conn1=Mockito.mock(HttpConnection.class);
  Mockito.when(connFactory.create(Mockito.eq("somehost"))).thenReturn(conn1);
  LocalConnPool pool=new LocalConnPool(connFactory,2,2);
  Future<LocalPoolEntry> future1=pool.lease("somehost",null);
  LocalPoolEntry entry1=future1.get(1,TimeUnit.SECONDS);
  Assert.assertNotNull(entry1);
  Mockito.verify(connFactory,Mockito.times(1)).create(Mockito.eq("somehost"));
  entry1.updateExpiry(1,TimeUnit.MILLISECONDS);
  pool.release(entry1,true);
  Thread.sleep(200L);
  Future<LocalPoolEntry> future2=pool.lease("somehost",null);
  LocalPoolEntry entry2=future2.get(1,TimeUnit.SECONDS);
  Assert.assertNotNull(entry2);
  Mockito.verify(connFactory,Mockito.times(2)).create(Mockito.eq("somehost"));
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(0,totals.getAvailable());
  Assert.assertEquals(1,totals.getLeased());
  PoolStats stats=pool.getStats("somehost");
  Assert.assertEquals(0,stats.getAvailable());
  Assert.assertEquals(1,stats.getLeased());
}
