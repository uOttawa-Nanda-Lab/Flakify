@Test public void testRequestContentInvalidInput() throws Exception {
  RequestContent interceptor=new RequestContent();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
