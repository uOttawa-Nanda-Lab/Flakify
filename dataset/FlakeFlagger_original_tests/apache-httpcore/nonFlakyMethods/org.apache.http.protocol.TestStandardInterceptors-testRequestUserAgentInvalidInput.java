@Test public void testRequestUserAgentInvalidInput() throws Exception {
  RequestUserAgent interceptor=new RequestUserAgent();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
