@Test public void testResponseServerInvalidInput() throws Exception {
  ResponseServer interceptor=new ResponseServer();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
