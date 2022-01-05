@Test public void testCloseExpired() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  HttpConnection conn1=Mockito.mock(HttpConnection.class);
  Mockito.when(conn1.isOpen()).thenReturn(Boolean.FALSE);
  HttpConnection conn2=Mockito.mock(HttpConnection.class);
  Mockito.when(conn2.isOpen()).thenReturn(Boolean.TRUE);
  Mockito.when(connFactory.create(Mockito.eq("somehost"))).thenReturn(conn1,conn2);
  LocalConnPool pool=new LocalConnPool(connFactory,2,2);
  Future<LocalPoolEntry> future1=pool.lease("somehost",null);
  LocalPoolEntry entry1=future1.get(1,TimeUnit.SECONDS);
  Assert.assertNotNull(entry1);
  Future<LocalPoolEntry> future2=pool.lease("somehost",null);
  LocalPoolEntry entry2=future2.get(1,TimeUnit.SECONDS);
  Assert.assertNotNull(entry2);
  entry1.updateExpiry(1,TimeUnit.MILLISECONDS);
  pool.release(entry1,true);
  Thread.sleep(200);
  entry2.updateExpiry(1000,TimeUnit.SECONDS);
  pool.release(entry2,true);
  pool.closeExpired();
  Mockito.verify(conn1).close();
  Mockito.verify(conn2,Mockito.never()).close();
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(1,totals.getAvailable());
  Assert.assertEquals(0,totals.getLeased());
  PoolStats stats=pool.getStats("somehost");
  Assert.assertEquals(1,stats.getAvailable());
  Assert.assertEquals(0,stats.getLeased());
}
