@Test public void testLeaseCacnel() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  HttpConnection conn1=Mockito.mock(HttpConnection.class);
  Mockito.when(connFactory.create(Mockito.eq("somehost"))).thenReturn(conn1);
  LocalConnPool pool=new LocalConnPool(connFactory,1,1);
  Future<LocalPoolEntry> future1=pool.lease("somehost",null);
  GetPoolEntryThread t1=new GetPoolEntryThread(future1);
  t1.start();
  t1.join(GRACE_PERIOD);
  Assert.assertTrue(future1.isDone());
  LocalPoolEntry entry1=t1.getEntry();
  Assert.assertNotNull(entry1);
  Future<LocalPoolEntry> future2=pool.lease("somehost",null);
  GetPoolEntryThread t2=new GetPoolEntryThread(future2);
  t2.start();
  Thread.sleep(5);
  Assert.assertFalse(future2.isDone());
  Assert.assertFalse(future2.isCancelled());
  future2.cancel(true);
  t2.join(GRACE_PERIOD);
  Assert.assertTrue(future2.isDone());
  Assert.assertTrue(future2.isCancelled());
  future2.cancel(true);
  future2.cancel(true);
}
