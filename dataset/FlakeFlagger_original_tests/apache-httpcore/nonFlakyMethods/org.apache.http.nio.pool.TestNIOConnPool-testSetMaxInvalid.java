@Test public void testSetMaxInvalid() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,2);
  try {
    pool.setMaxTotal(-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    pool.setMaxPerRoute(null,1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    pool.setMaxPerRoute("somehost",-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    pool.setDefaultMaxPerRoute(-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
}
