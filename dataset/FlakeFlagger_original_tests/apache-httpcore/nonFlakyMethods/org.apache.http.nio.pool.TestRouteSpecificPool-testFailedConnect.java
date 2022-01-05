@Test public void testFailedConnect() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  SessionRequest sessionRequest=Mockito.mock(SessionRequest.class);
  Mockito.when(sessionRequest.getException()).thenReturn(new IOException());
  BasicFuture<LocalPoolEntry> future=new BasicFuture<LocalPoolEntry>(null);
  pool.addPending(sessionRequest,future);
  Assert.assertEquals(1,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(1,pool.getPendingCount());
  pool.failed(sessionRequest);
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
  try {
    future.get();
    Assert.fail("ExecutionException should have been thrown");
  }
 catch (  ExecutionException ex) {
    Assert.assertTrue(ex.getCause() instanceof IOException);
  }
  Assert.assertEquals(0,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(0,pool.getPendingCount());
}
