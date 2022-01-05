@SuppressWarnings("unchecked") @Test public void testWaitingThreadQueuing() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  PoolEntryFuture<LocalPoolEntry> future1=Mockito.mock(PoolEntryFuture.class);
  PoolEntryFuture<LocalPoolEntry> future2=Mockito.mock(PoolEntryFuture.class);
  Assert.assertEquals(0,pool.getPendingCount());
  pool.queue(future1);
  Assert.assertEquals(1,pool.getPendingCount());
  pool.queue(null);
  Assert.assertEquals(1,pool.getPendingCount());
  pool.queue(future2);
  Assert.assertEquals(2,pool.getPendingCount());
  Assert.assertSame(future1,pool.nextPending());
  pool.unqueue(future1);
  Assert.assertEquals(1,pool.getPendingCount());
  Assert.assertSame(future2,pool.nextPending());
  pool.unqueue(null);
  Assert.assertEquals(0,pool.getPendingCount());
  pool.unqueue(future2);
  Assert.assertNull(pool.nextPending());
}
