@Test public void testShutdown() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  HttpConnection conn1=Mockito.mock(HttpConnection.class);
  Mockito.when(connFactory.create(Mockito.eq("somehost"))).thenReturn(conn1);
  HttpConnection conn2=Mockito.mock(HttpConnection.class);
  Mockito.when(connFactory.create(Mockito.eq("otherhost"))).thenReturn(conn2);
  LocalConnPool pool=new LocalConnPool(connFactory,2,2);
  Future<LocalPoolEntry> future1=pool.lease("somehost",null);
  LocalPoolEntry entry1=future1.get(1,TimeUnit.SECONDS);
  Assert.assertNotNull(entry1);
  Future<LocalPoolEntry> future2=pool.lease("otherhost",null);
  LocalPoolEntry entry2=future2.get(1,TimeUnit.SECONDS);
  Assert.assertNotNull(entry2);
  pool.release(entry2,true);
  PoolStats totals=pool.getTotalStats();
  Assert.assertEquals(1,totals.getAvailable());
  Assert.assertEquals(1,totals.getLeased());
  pool.shutdown(1000);
  Assert.assertTrue(pool.isShutdown());
  pool.shutdown(1000);
  pool.shutdown(1000);
  Mockito.verify(conn1,Mockito.atLeastOnce()).close();
  Mockito.verify(conn2,Mockito.atLeastOnce()).close();
  try {
    pool.lease("somehost",null);
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException expected) {
  }
  pool.release(new LocalPoolEntry("somehost",Mockito.mock(HttpConnection.class)),true);
}
