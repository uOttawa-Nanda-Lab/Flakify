@Test public void testLeaseIllegal() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  LocalConnPool pool=new LocalConnPool(connFactory,2,10);
  try {
    pool.lease(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
}
