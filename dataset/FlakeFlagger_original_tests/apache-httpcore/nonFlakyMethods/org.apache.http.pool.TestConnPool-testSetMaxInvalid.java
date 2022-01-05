@Test public void testSetMaxInvalid() throws Exception {
  HttpConnectionFactory connFactory=Mockito.mock(HttpConnectionFactory.class);
  LocalConnPool pool=new LocalConnPool(connFactory,2,2);
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
