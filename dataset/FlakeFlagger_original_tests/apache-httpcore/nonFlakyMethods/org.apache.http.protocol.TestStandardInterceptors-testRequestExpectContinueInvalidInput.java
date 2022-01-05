@Test public void testRequestExpectContinueInvalidInput() throws Exception {
  RequestExpectContinue interceptor=new RequestExpectContinue();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
