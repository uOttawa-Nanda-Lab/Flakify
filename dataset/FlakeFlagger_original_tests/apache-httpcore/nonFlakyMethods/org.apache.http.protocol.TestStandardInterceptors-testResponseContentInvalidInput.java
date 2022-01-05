@Test public void testResponseContentInvalidInput() throws Exception {
  ResponseContent interceptor=new ResponseContent();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
