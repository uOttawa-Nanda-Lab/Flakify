@Test public void testRequestTargetHostInvalidInput() throws Exception {
  RequestTargetHost interceptor=new RequestTargetHost();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    interceptor.process(new BasicHttpRequest("GET","/"),null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
