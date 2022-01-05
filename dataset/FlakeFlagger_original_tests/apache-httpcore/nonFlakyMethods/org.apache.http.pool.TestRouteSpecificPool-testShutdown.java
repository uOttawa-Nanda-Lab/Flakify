@SuppressWarnings("unchecked") @Test public void testShutdown() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  HttpConnection conn1=Mockito.mock(HttpConnection.class);
  LocalPoolEntry entry1=pool.add(conn1);
  HttpConnection conn2=Mockito.mock(HttpConnection.class);
  LocalPoolEntry entry2=pool.add(conn2);
  PoolEntryFuture<LocalPoolEntry> future1=Mockito.mock(PoolEntryFuture.class);
  pool.queue(future1);
  Assert.assertNotNull(entry1);
  Assert.assertNotNull(entry2);
  pool.free(entry1,true);
  Assert.assertEquals(2,pool.getAllocatedCount());
  Assert.assertEquals(1,pool.getAvailableCount());
  Assert.assertEquals(1,pool.getLeasedCount());
  Assert.assertEquals(1,pool.getPendingCount());
  pool.shutdown();
  Assert.assertEquals(0,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(0,pool.getPendingCount());
  Mockito.verify(future1).cancel(true);
  Mockito.verify(conn2).close();
  Mockito.verify(conn1).close();
}
