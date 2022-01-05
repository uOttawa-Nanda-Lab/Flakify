@Test public void testLeaseIOException() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  Mockito.doThrow(new IOException("Oppsie")).when(connFactory).create("somehost");
  LocalConnPool pool=new LocalConnPool(connFactory,2,10);
  Future<LocalPoolEntry> future1=pool.lease("somehost",null);
  GetPoolEntryThread t1=new GetPoolEntryThread(future1);
  t1.start();
  t1.join(GRACE_PERIOD);
  Assert.assertTrue(future1.isDone());
  Assert.assertTrue(t1.getException() instanceof ExecutionException);
  Assert.assertTrue(t1.getException().getCause() instanceof IOException);
  Assert.assertFalse(future1.isCancelled());
}
