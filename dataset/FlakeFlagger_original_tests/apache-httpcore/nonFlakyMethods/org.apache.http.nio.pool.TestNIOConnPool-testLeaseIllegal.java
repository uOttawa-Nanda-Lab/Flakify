@Test public void testLeaseIllegal() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,10);
  try {
    pool.lease(null,null,0,TimeUnit.MILLISECONDS,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    pool.lease("somehost",null,0,null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
}
