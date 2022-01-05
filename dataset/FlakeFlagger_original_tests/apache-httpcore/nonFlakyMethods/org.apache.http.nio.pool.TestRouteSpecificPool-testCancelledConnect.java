@Test public void testCancelledConnect() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  SessionRequest sessionRequest=Mockito.mock(SessionRequest.class);
  BasicFuture<LocalPoolEntry> future=new BasicFuture<LocalPoolEntry>(null);
  pool.addPending(sessionRequest,future);
  Assert.assertEquals(1,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(1,pool.getPendingCount());
  pool.cancelled(sessionRequest);
  Assert.assertTrue(future.isDone());
  Assert.assertTrue(future.isCancelled());
  Assert.assertEquals(0,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(0,pool.getPendingCount());
}
