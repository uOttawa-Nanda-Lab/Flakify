@Test public void testSuccessfulConnect() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  IOSession session=Mockito.mock(IOSession.class);
  SessionRequest sessionRequest=Mockito.mock(SessionRequest.class);
  Mockito.when(sessionRequest.getSession()).thenReturn(session);
  BasicFuture<LocalPoolEntry> future=new BasicFuture<LocalPoolEntry>(null);
  pool.addPending(sessionRequest,future);
  Assert.assertEquals(1,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(1,pool.getPendingCount());
  LocalPoolEntry entry=pool.completed(sessionRequest,session);
  Assert.assertNotNull(entry);
  Assert.assertSame(session,entry.getConnection());
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
  Assert.assertEquals(1,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(1,pool.getLeasedCount());
  Assert.assertEquals(0,pool.getPendingCount());
}
